package com.homework.server.homework_server.oembed.service

import com.homework.server.homework_server.oembed.dto.response.OEmbedResponseDto

interface OEmbedService {
    fun getOEmbedInfo(url: String): OEmbedResponseDto?
}