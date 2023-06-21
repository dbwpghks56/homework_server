package com.homework.server.homework_server.oembed.web

import com.homework.server.homework_server.oembed.dto.response.OEmbedResponseDto
import com.homework.server.homework_server.oembed.service.OEmbedService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/OEmbed")
class OEmbedController(
    private val oEmbedService: OEmbedService
) {
    @GetMapping()
    fun getOEmbedInfo(
        url:String
    ): ResponseEntity<OEmbedResponseDto> {
        return ResponseEntity.ok(oEmbedService.getOEmbedInfo(url))
    }
}