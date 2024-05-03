package ru.kafka.emailnotificationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EmailNotificationServiceApplication

fun main(args: Array<String>) {
	runApplication<EmailNotificationServiceApplication>(*args)
}
