package ru.kafka.productservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kafka.productservice.model.CreateProductDto
import ru.kafka.productservice.service.ProductService


@RestController
@RequestMapping("product")
class ProductController(
    val productService: ProductService
) {

    @PostMapping("/sync")
    fun createProductSync(@RequestBody product: CreateProductDto): String {
        return productService.createProductSync(product)
    }

    @PostMapping("/async")
    fun createProductAsync(@RequestBody product: CreateProductDto): String {
        return productService.createProductAsync(product)
    }

}