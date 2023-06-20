package com.homework.server.homework_server.oembedprovider.web

import com.homework.server.homework_server.oembedprovider.dto.response.ProviderResponseDto
import com.homework.server.homework_server.oembedprovider.service.ProviderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/provider")
@Tag(name = "[App] oEmbed Provider", description = "oEmbed Provider API")
class ProviderController(
    private val providerService: ProviderService
) {
    @GetMapping()
    @Tag(name = "[App] oEmbed Provider")
    @Operation(
        summary = "oEmbed Provider 전체 조회", description = "oEmbed Provider 의 전체 데이터 조회 <br> oEmbed 에 등록된 모든 주소를 사용하기 위해"
    )
    @ResponseStatus(HttpStatus.OK)
    fun getProviders(): ResponseEntity<List<ProviderResponseDto>> {
        return ResponseEntity.ok(providerService.getProviders())
    }
}