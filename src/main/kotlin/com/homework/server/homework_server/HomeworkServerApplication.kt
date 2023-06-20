package com.homework.server.homework_server

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HomeworkServerApplication

fun main(args: Array<String>) {
	val springApplication: SpringApplication = SpringApplication(HomeworkServerApplication::class.java)
	springApplication.setLogStartupInfo(false)
	springApplication.run(*args)
}
