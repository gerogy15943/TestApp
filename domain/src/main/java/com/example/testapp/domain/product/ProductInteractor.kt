package com.example.testapp.domain.product

import com.example.testapp.domain.product.ProductDomain

interface ProductInteractor {
    suspend fun fetchProducts(): List<ProductDomain>
    suspend fun fetchProduct(id: Int): ProductDomain
}