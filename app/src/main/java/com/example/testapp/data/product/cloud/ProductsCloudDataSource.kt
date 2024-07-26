package com.example.testapp.data.product.cloud

import com.example.testapp.data.product.ProductDTO
import com.example.testapp.core.retrofit.RetrofitService

interface ProductsCloudDataSource {
    suspend fun fetchProducts(): List<ProductDTO>

    class BaseProductsCloudDataSource(private val retrofitService: RetrofitService): ProductsCloudDataSource {
        override suspend fun fetchProducts(): List<ProductDTO> {
            return retrofitService.fetchService(ProductsService::class.java).fetchProducts().execute().body() ?: emptyList()
        }
    }

}