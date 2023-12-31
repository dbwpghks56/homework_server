package com.homework.server.homework_server.boot.advisor

import com.homework.server.homework_server.boot.dto.response.CommonResponseDto
import com.homework.server.homework_server.boot.exception.RestException
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
@RequiredArgsConstructor
class CustomExceptionHandler<T> {

    @ExceptionHandler(RestException::class)
    fun restExceptionHandler(
        e: RestException
    ): ResponseEntity<CommonResponseDto<T>> {
        e.printStackTrace()
        val responseDto = CommonResponseDto<T>(
            success = false,
            status = e.httpStatus,
            message = e.message ?: "메세지가 없습니다.",
            data = null
        )

        return ResponseEntity.status(e.httpStatus).body(responseDto)
    }
}