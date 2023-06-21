package com.homework.server.homework_server.oembed.service.impl

import com.google.gson.Gson
import com.homework.server.homework_server.boot.exception.RestException
import com.homework.server.homework_server.oembed.dto.response.OEmbedResponseDto
import com.homework.server.homework_server.oembed.service.OEmbedService
import com.homework.server.homework_server.oembedprovider.dto.response.ProviderResponseDto
import com.homework.server.homework_server.oembedprovider.service.ProviderService
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.function.Predicate

@Service
class OEmbedServiceImpl(
    private val providerService: ProviderService
):OEmbedService {
    var stack: List<ProviderResponseDto> = emptyList()

    @Cacheable("oEmbedCache")
    override fun getOEmbedInfo(url: String): OEmbedResponseDto? {
        if (stack.isEmpty()) {
            stack = providerService.getProviders() ?: throw RestException(HttpStatus.BAD_REQUEST, "Provider를 가져올 수 없습니다.")
        }
        val providerUrl: String = url.split("/")[2]
        val providerData = stack.first { providerResponseDto: ProviderResponseDto -> providerResponseDto.provider_url?.contains(providerUrl)
            ?: throw  RestException(HttpStatus.NOT_FOUND, "Provider에 없는 자료입니다.") }
        var oEmbedUrl = providerData.endpoints?.first()?.url

        if (oEmbedUrl != null) {
            if (oEmbedUrl.contains("{format}")){
                oEmbedUrl = oEmbedUrl.replace("{format}", "json")
                oEmbedUrl = "$oEmbedUrl/?url=$url"
            }
            else{
                oEmbedUrl = "$oEmbedUrl?url=$url&format=json"
            }
            val okHttp = OkHttpClient()
            val request = Request.Builder()
                .url(oEmbedUrl)
                .get()
                .build()

            val resp = okHttp.newCall(request).execute()

            try {
                if (resp.isSuccessful) {
                    val responseBody = resp.body?.string()

                    val oEmbedResponseDto = Gson().fromJson(responseBody, OEmbedResponseDto::class.java)

                    return oEmbedResponseDto
                }
            } catch (e: Exception) {
                throw  RestException(HttpStatus.BAD_REQUEST, e.message.toString())
            } finally {
                resp.close()
            }

        }

        return null
    }
}






