package ru.kafka.productservice.model

import java.math.BigDecimal

data class CreateProductDto(
    val title: String,
    val price:BigDecimal,
    val quantity: Int
)

data class ProductCreatedEvent(
    val productId: String,
    val title: String,
    val price: BigDecimal,
    val quantity: Int
)