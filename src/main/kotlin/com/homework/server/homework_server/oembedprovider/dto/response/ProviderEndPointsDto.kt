package com.homework.server.homework_server.oembedprovider.dto.response

data class ProviderEndPointsDto(
    val schemes: List<String>? = null,
    val url : String? = null,
    val discovery: Boolean? = null,
    val formats: List<String>? = null
)
