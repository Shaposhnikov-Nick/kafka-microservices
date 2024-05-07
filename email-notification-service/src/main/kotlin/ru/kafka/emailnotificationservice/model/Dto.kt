package ru.kafka.emailnotificationservice.model

import java.math.BigDecimal

data class ProductCreatedEvent(
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)