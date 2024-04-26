package ru.kafka.productservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

const val ProductCreatedEventTopic = "product-created-event-topic"

@Configuration
class KafkaConfig {

    @Bean
    fun createTopic(): NewTopic {
        return TopicBuilder
            .name(ProductCreatedEventTopic)
            .partitions(3)
            .replicas(3)
            .configs(mapOf("min.insync.replicas" to "2"))
            .build()
    }
}