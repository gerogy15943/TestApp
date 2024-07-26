package com.example.testapp.domain

import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.data.products.cache.TestProductsCacheDataSource
import com.example.testapp.domain.core.ExceptionHandler
import com.example.testapp.domain.core.Generic
import com.example.testapp.domain.core.NotFound
import com.example.testapp.domain.core.UnknownHost
import com.example.testapp.domain.product.ProductDomain
import com.example.testapp.domain.product.ProductsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductsInteractorTest {

    private lateinit var repository: ProductsRepository
    private lateinit var exceptionHandler: ExceptionHandler

    @Before
    fun setup() {
        repository = TestRepository(TestProductsCacheDataSource(), ProductCacheToDomainMapper.BaseProductCacheToDomainMapper())
        exceptionHandler = ExceptionHandler.BaseExceptionHandler()
    }

    @Test
    fun failNotFoundException(){
        runBlocking {
            val interactor = TestProductsInteractor(repository, exceptionHandler, ExceptionType.NOTFOUND)
            val actual = interactor.fetchProduct(-1)
            val expected = ProductDomain.Error(NotFound())
            assertEquals(expected.toString(), actual.toString())
        }
    }

    @Test
    fun failGenericException(){
        runBlocking {
            val interactor = TestProductsInteractor(repository, exceptionHandler, ExceptionType.GENERIC)
            val actual = interactor.fetchProducts()
            val expected = listOf(ProductDomain.Error(Generic()))
            assertEquals(expected.toString(), actual.toString())
        }
    }

    @Test
    fun failUnknownException(){
        runBlocking {
            val interactor = TestProductsInteractor(repository, exceptionHandler, ExceptionType.UNKNOWNHOST)
            val actual = interactor.fetchProducts()
            val expected = listOf(ProductDomain.Error(UnknownHost()))
            assertEquals(expected.toString(), actual.toString())
        }
    }

    @Test
    fun success() {
        runBlocking {
            val interactor = TestProductsInteractor(repository, exceptionHandler, ExceptionType.NONE)
            val expected = listOf(
                ProductDomain.Success(1, "Продукт 1", 1.00, "Описание 1", "Категория 1", "http://localhost/1", 1.00),
                ProductDomain.Success(2, "Продукт 2", 2.00, "Описание 2", "Категория 1", "http://localhost/2", 1.00),
                ProductDomain.Success(3, "Продукт 3", 3.00, "Описание 3", "Категория 1", "http://localhost/3", 1.00))
            val productsDomain = interactor.fetchProducts()
            assertEquals(expected, productsDomain)
        }
    }

    private inner class TestRepository(
        private val cacheDataSource: TestProductsCacheDataSource,
        private val mapperToDomain: ProductCacheToDomainMapper): ProductsRepository{
        override suspend fun fetchProducts(): List<ProductDomain> {
            return cacheDataSource.fetchProducts().map { it.map(mapperToDomain) }
        }

        override suspend fun fetchProduct(id: Int): ProductDomain {
            return cacheDataSource.fetchProduct(id).map(mapperToDomain)
        }
    }

}