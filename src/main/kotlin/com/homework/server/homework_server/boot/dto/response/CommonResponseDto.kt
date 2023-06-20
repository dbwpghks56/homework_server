package com.homework.server.homework_server.boot.dto.response

import lombok.NoArgsConstructor
import org.springframework.http.HttpStatus

@NoArgsConstructor
data class CommonResponseDto<T>(
    var success: Boolean,
    var status: HttpStatus,
    var message: String,
    var data: T?
) {
}