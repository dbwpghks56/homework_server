package com.homework.server.homework_server.oembed.web

import com.homework.server.homework_server.oembed.dto.response.OEmbedResponseDto
import com.homework.server.homework_server.oembed.service.OEmbedService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/oEmbed")
@Tag(name = "[App] oEmbed Data", description = "oEmbed Data API")
class OEmbedController(
    private val oEmbedService: OEmbedService
) {
    @GetMapping()
    @Tag(name = "[App] oEmbed Data")
    @Operation(
        summary = "oEmbed 해당 데이터 조회",
        description = "사용자에게 받은 url 데이터를 이용해서 oEmbed 데이터 조회"
    )
    @ResponseStatus(HttpStatus.OK)
    fun getOEmbedInfo(
        url:String
    ): ResponseEntity<OEmbedResponseDto> {
        return ResponseEntity.ok(oEmbedService.getOEmbedInfo(url))
    }
}