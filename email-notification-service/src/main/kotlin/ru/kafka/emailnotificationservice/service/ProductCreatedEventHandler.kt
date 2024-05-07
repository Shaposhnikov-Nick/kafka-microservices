package ru.kafka.emailnotificationservice.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import ru.kafka.emailnotificationservice.model.ProductCreatedEvent

const val ProductCreatedEventTopic = "product-created-event-topic"

@Component
class ProductCreatedEventHandler {

    val log: Logger = LoggerFactory.getLogger(this::class.java)

    @KafkaListener(topics = [ProductCreatedEventTopic])
    fun handle(event: ProductCreatedEvent) {
        log.info("Received event: {}", event.title)
    }
}