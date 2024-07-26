package com.example.testapp.domain.product

interface ProductRepository {

    suspend fun fetchProducts():List<ProductDomain>
    suspend fun fetchProduct(id: Int): ProductDomain
}