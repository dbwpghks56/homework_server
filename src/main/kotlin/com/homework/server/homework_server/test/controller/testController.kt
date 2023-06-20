package com.homework.server.homework_server.test.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class testController() {
    @GetMapping()
    fun test(
        test: String?
    ) : ResponseEntity<String> {
        return ResponseEntity.ok(test)
    }
}