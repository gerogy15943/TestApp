package com.example.testapp.data.products.cloud

import com.example.testapp.data.product.ProductDTO
import com.example.testapp.data.product.cloud.ProductsCloudDataSource
import java.net.UnknownHostException

class TestProductsCloudDataSource(var success: Boolean): ProductsCloudDataSource {
    private val products = listOf(
        ProductDTO(1, "Продукт 1", 1.00, "Описание 1", "Категория 1", "http://localhost/1", ProductDTO.Rating(1.00, 10)),
        ProductDTO(2, "Продукт 2", 2.00, "Описание 2", "Категория 1", "http://localhost/2", ProductDTO.Rating(1.00, 10)),
        ProductDTO(3, "Продукт 3", 3.00, "Описание 3", "Категория 1", "http://localhost/3", ProductDTO.Rating(1.00, 10)))
    override suspend fun fetchProducts(): List<ProductDTO> {
        if(success)
            return products
        else
            throw UnknownHostException()
    }
}