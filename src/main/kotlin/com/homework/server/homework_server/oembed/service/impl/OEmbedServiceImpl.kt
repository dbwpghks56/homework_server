package com.homework.server.homework_server.oembed.service.impl

import com.google.gson.Gson
import com.homework.server.homework_server.boot.exception.RestException
import com.homework.server.homework_server.oembed.dto.response.OEmbedResponseDto
import com.homework.server.homework_server.oembed.service.OEmbedService
import com.homework.server.homework_server.oembedprovider.dto.response.ProviderResponseDto
import com.homework.server.homework_server.oembedprovider.service.ProviderService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OEmbedServiceImpl(
    private val providerService: ProviderService
):OEmbedService {
    var providerStack: List<ProviderResponseDto> = emptyList()

    //key 는 매개변수로 지정 unless 를 이용해 결괏값이 null이면 저장하지 않음
    @Cacheable(value = ["oEmbedCache"], key = "#url", unless = "#result == null")
    override fun getOEmbedInfo(url: String): OEmbedResponseDto? {
        // OEmbed Provider 에 해당하는 값을 이미 조회해서 데이터를 가지고 있으면 다시 조회하지 않음
        // 조회한 데이터가 없다면 다시 조회
        if (providerStack.isEmpty()) {
            providerStack = providerService.getProviders() ?: throw RestException(HttpStatus.BAD_REQUEST, "Provider를 가져올 수 없습니다.")
        }

        // 가져온 provider 데이터와 대조하기 위해 사용자가 입력한 url 에 대한 도메인 데이터 가져오기
        var providerUrl: String = url.split("/")[2]

        if (providerUrl.contains("www")) {
            providerUrl = providerUrl.replace("www", "")
        }

        // 도메인 데이터 이용해서 해당 도메인에 관한 OEmbed provider 데이터 가져오기
        val providerData = providerStack.firstOrNull { providerResponseDto ->
            providerResponseDto.endpoints?.any { endpoint ->
                endpoint.schemes?.any { schema ->
                    schema.contains(providerUrl)
                } == true
            } == true
        } ?: throw RestException(HttpStatus.NOT_FOUND, "Provider에 없는 자료입니다.")


        // endpoints 가 요소를 하나 가지고있는 배열이기 때문에 first 를 이용해서 OEmbed 엔드 포인트 가져오기
        var oEmbedUrl = providerData.endpoints?.first()?.url

        // 엔드포인트를 가지고 있을 경우
        if (oEmbedUrl != null) {
            // {format} 을 가지고 있으면 해당 값을 json 으로 바꾸고 아니라면 요청 url 에 json 추가해서 OEmbed api 요청
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

            // api 요청
            val resp = okHttp.newCall(request).execute()

            try {
                // 요청 성공시 해당 Dto 로 역직렬화하여 반환
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






