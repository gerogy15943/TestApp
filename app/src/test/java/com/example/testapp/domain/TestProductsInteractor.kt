package com.example.testapp.domain

import android.content.res.Resources.NotFoundException
import com.example.testapp.domain.core.ExceptionHandler
import com.example.testapp.domain.product.ProductDomain
import com.example.testapp.domain.product.ProductInteractor
import com.example.testapp.domain.product.ProductsRepository
import kotlinx.coroutines.test.TestCoroutineDispatcher
import java.net.UnknownHostException

class TestProductsInteractor(private val repository: ProductsRepository,
                             private val exceptionHandler: ExceptionHandler,
                             private val exception: ExceptionType): ProductInteractor {
    override suspend fun fetchProducts(): List<ProductDomain> {
        try {
            if(exception == ExceptionType.NONE) {
                val productsDomain = repository.fetchProducts()
                return productsDomain
            } else if(exception == ExceptionType.UNKNOWNHOST)
                throw UnknownHostException()
            else if(exception == ExceptionType.NOTFOUND)
                throw NotFoundException()
            else
                throw SecurityException()
            } catch (e: java.lang.Exception) {
                return listOf(ProductDomain.Error(exceptionHandler.map(e)))
            }
    }

    override suspend fun fetchProduct(id: Int): ProductDomain {
        return try {
            repository.fetchProduct(id)
        } catch (e: java.lang.Exception) {
            ProductDomain.Error(exceptionHandler.map(e))
        }
    }
}

enum class ExceptionType{
    NONE, UNKNOWNHOST, NOTFOUND, GENERIC
}