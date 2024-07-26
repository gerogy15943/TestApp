package com.example.testapp.domain.product

interface ProductsRepository {
    suspend fun fetchProducts(): List<ProductDomain>
    suspend fun fetchProduct(id: Int): ProductDomain
}