package com.example.testapp.data.products

import com.example.testapp.data.product.BaseProductsRepository
import com.example.testapp.data.product.ProductDTO
import com.example.testapp.data.product.cache.ProductCache
import com.example.testapp.data.product.cache.ProductsCacheDataSource
import com.example.testapp.data.product.cloud.ProductsCloudDataSource
import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.data.product.mappers.ProductDtoToCacheMapper
import com.example.testapp.data.products.cache.TestProductsCacheDataSource
import com.example.testapp.data.products.cloud.TestProductsCloudDataSource
import com.example.testapp.domain.product.ProductDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.net.UnknownHostException


class TestProductsRepository{

    @Test
    fun fail(){
        val cloudDataSource = TestProductsCloudDataSource(false)
        val cacheDataSource = TestProductsCacheDataSource()
        val toCacheMapper = ProductDtoToCacheMapper.BaseProductDtoToCacheMapper()
        val toDomainMapper = ProductCacheToDomainMapper.BaseProductCacheToDomainMapper()
        val repository = BaseProductsRepository(cacheDataSource, cloudDataSource, toCacheMapper, toDomainMapper)
        try {
            runBlocking {
                repository.fetchProducts()
            }
        } catch (e: java.lang.Exception){
            assertEquals(e.message, UnknownHostException().message)
        }
    }

    @Test
    fun success(){
        val cloudDataSource = TestProductsCloudDataSource(true)
        val cacheDataSource = TestProductsCacheDataSource()
        val toCacheMapper = ProductDtoToCacheMapper.BaseProductDtoToCacheMapper()
        val toDomainMapper = ProductCacheToDomainMapper.BaseProductCacheToDomainMapper()
        val repository = BaseProductsRepository(cacheDataSource, cloudDataSource, toCacheMapper, toDomainMapper)
        runBlocking {
            val expected = listOf(
                ProductDomain.Success(1, "Продукт 1", 1.00, "Описание 1", "Категория 1", "http://localhost/1", 1.00),
                ProductDomain.Success(2, "Продукт 2", 2.00, "Описание 2", "Категория 1", "http://localhost/2", 1.00),
                ProductDomain.Success(3, "Продукт 3", 3.00, "Описание 3", "Категория 1", "http://localhost/3", 1.00))
            val actualProducts = repository.fetchProducts()
            assertEquals(expected, actualProducts)
        }
    }
}