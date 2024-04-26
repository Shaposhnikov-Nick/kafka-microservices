package ru.kafka.productservice.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.kafka.productservice.config.ProductCreatedEventTopic
import ru.kafka.productservice.model.CreateProductDto
import ru.kafka.productservice.model.ProductCreatedEvent
import java.util.*

interface ProductService {
    fun createProductSync(product: CreateProductDto): String
    fun createProductAsync(product: CreateProductDto): String
}


@Service
class ProductServiceImpl(
    val kafkaTemplate: KafkaTemplate<String, ProductCreatedEvent>
) : ProductService {

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun createProductSync(product: CreateProductDto): String {
        val productId = UUID.randomUUID().toString()
        val productCreatedEvent = ProductCreatedEvent(
            productId,
            product.title,
            product.price,
            product.quantity
        )

        try {
            val result = kafkaTemplate.send(ProductCreatedEventTopic, productId, productCreatedEvent).get()
            log.info("Topic: ${result.recordMetadata.topic()}")
            log.info("Partition: ${result.recordMetadata.partition()}")
            log.info("Offset: ${result.recordMetadata.offset()}")
        } catch (e: Exception) {
            log.error("Failed to send message ${e.message}")
        }

        log.info("Return product with id $productId)")
        return productId
    }

    override fun createProductAsync(product: CreateProductDto): String {
        val productId = UUID.randomUUID().toString()
        val productCreatedEvent = ProductCreatedEvent(
            productId,
            product.title,
            product.price,
            product.quantity
        )

        val futureResult = kafkaTemplate.send(ProductCreatedEventTopic, productId, productCreatedEvent)
        futureResult.whenComplete { result, exception ->
            if (exception != null) log.error("Failed to send message ${exception.message}")
            else log.info("Message sent successfully ${result.recordMetadata}")
        }

        log.info("Return product with id $productId)")
        return productId
    }

}