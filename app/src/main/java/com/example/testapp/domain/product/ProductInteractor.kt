package com.example.testapp.domain.product

import com.example.testapp.domain.core.ExceptionHandler

interface ProductInteractor {
    suspend fun fetchProducts(): List<ProductDomain>
    suspend fun fetchProduct(id: Int): ProductDomain

    class BaseProductInteractor(
        private val repository: ProductsRepository,
        private val exceptionHandler: ExceptionHandler): ProductInteractor{
        override suspend fun fetchProducts(): List<ProductDomain> {
             return try {
                 repository.fetchProducts()
             } catch (e: java.lang.Exception){
                 listOf(ProductDomain.Error(exceptionHandler.map(e)))
             }
        }

        override suspend fun fetchProduct(id: Int): ProductDomain {
            return try {
                repository.fetchProduct(id)
            } catch (e: java.lang.Exception){
                ProductDomain.Error(exceptionHandler.map(e))
            }
        }
    }
}