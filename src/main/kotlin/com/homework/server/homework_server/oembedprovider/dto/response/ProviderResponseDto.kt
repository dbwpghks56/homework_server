package com.homework.server.homework_server.oembedprovider.dto.response

import io.swagger.v3.oas.annotations.media.Schema


data class ProviderResponseDto(
    @Schema(description = "provider 이름")
    val provider_name: String? = null,
    @Schema(description = "provider 공식 url")
    val provider_url: String? = null,
    @Schema(description = "해당 provider 에서 사용할 수 있는 endpoints")
    val endpoints: List<ProviderEndPointsDto>? = null
)
