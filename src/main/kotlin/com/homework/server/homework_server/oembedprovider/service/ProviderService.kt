package com.homework.server.homework_server.oembedprovider.service

import com.homework.server.homework_server.oembedprovider.dto.response.ProviderResponseDto

interface ProviderService {
    fun getProviders(): List<ProviderResponseDto>?
}