package com.homework.server.homework_server.oembed.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime
import java.util.Date

data class OEmbedResponseDto(
    @Schema(
        description = "제목"
    )
    val title: String? = null,
    @Schema(
        description = "해당 링크 내용물에 대한 type"
    )
    val type: String? = null,
    @Schema(
        description = "버전"
    )
    val version: Float? = null,
    @Schema(
        description = "링크에 대한 정보를 제공하는 곳의 이름"
    )
    val provider_name: String? = null,
    @Schema(
        description = "링크에 대한 정보를 제공하는 곳의 주소"
    )
    val provider_url: String? = null,
    @Schema(
        description = "링크에 해당하는 정보를 게시한 사람"
    )
    val author_name : String? = null,
    @Schema(
        description = "링크에 해당하는 정보를 게시한 페이지(계정) 주소"
    )
    val author_url: String? = null,
    val is_plus:Int? = null,
    @Schema(
        description = "링크에 대한 내용을 게시할 때 쓰는 html"
    )
    val html: String? = null,
    @Schema(
        description = "링크에 대한 내용을 게시할 때 쓰는 html의 넓이"
    )
    val width: Any? = null,
    @Schema(
        description = "링크에 대한 내용을 게시할 때 쓰는 html의 높이"
    )
    val height: Any? = null,
    val duration: Int? = null,
    @Schema(
        description = "링크에 대한 내용의 설명"
    )
    val description: String? = null,
    @Schema(
        description = "링크에 대한 내용의 썸네일"
    )
    val thumbnail_url: String? = null,
    @Schema(
        description = "링크에 대한 내용의 썸네일 넓이"
    )
    val thumbnail_width: Any? = null,
    @Schema(
        description = "링크에 대한 내용의 썸네일 높이"
    )
    val thumbnail_height: Any? = null,
    val thumbnail_url_with_play_button: String? = null,
    @Schema(
        description = "링크에 대한 내용의 업로드 일"
    )
    val upload_date: String? = null,
    @Schema(
        description = "링크에 대한 내용의 id"
    )
    val video_id: Int? = null,
    val cache_age: Long? = null,
    val url:String? = null
)
