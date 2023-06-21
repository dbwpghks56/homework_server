package com.homework.server.homework_server.oembed.dto.response

import java.time.LocalDateTime
import java.util.Date

data class OEmbedResponseDto(
    val title: String? = null,
    val type: String? = null,
    val version: Float? = null,
    val provider_name: String? = null,
    val provider_url: String? = null,
    val author_name : String? = null,
    val author_url: String? = null,
    val is_plus:Int? = null,
    val html: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val duration: Int? = null,
    val description: String? = null,
    val thumbnail_url: String? = null,
    val thumbnail_width: Int? = null,
    val thumbnail_height: Int? = null,
    val thumbnail_url_with_play_button: String? = null,
    val upload_date: String? = null,
    val video_id: Int? = null,
    val cache_age: Long? = null,
    val url:String? = null
)
