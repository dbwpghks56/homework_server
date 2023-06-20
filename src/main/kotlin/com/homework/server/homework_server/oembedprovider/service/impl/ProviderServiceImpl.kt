package com.homework.server.homework_server.oembedprovider.service.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.homework.server.homework_server.oembedprovider.dto.response.ProviderResponseDto
import com.homework.server.homework_server.oembedprovider.service.ProviderService
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class ProviderServiceImpl(): ProviderService {
    // oEmbed의 모든 경우의 수를 활용하기 위해 provider 받아오기
    override fun getProviders(): List<ProviderResponseDto>? {
        // 외부 api 사용하기 위해 Okhttp 객체 생성
        val client: OkHttpClient = OkHttpClient()

        // request 생성
        val request = Request.Builder()
            .url("https://oembed.com/providers.json")
            .get()
            .build()

        try {
            // request 발행해서 받은 response 저장
            val response = client.newCall(request).execute()

            // response 가 성공적일 경우 실행
            if (response.isSuccessful) {
                // response 의 데이터를 string 형태로 받아온다.
                val responseBody = response.body?.string()
                response.close()

                // 역직렬화를 위해 gson 객체 생성
                val gson = Gson()
                // TypeToken 클래스를 사용하여 List<ProviderResponseDto>의 제네릭 타입을 지정
                val listType = object : TypeToken<List<ProviderResponseDto>>() {}.type

                // 역직렬화 실행
                val providers: List<ProviderResponseDto> = gson.fromJson(responseBody, listType)
                // 역직렬화된 provider 데이터 반환
                return providers
            } else {
                println("Response was not successful: ${response.code}")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }
}