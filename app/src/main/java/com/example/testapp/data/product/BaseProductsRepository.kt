package com.example.testapp.data.product

import com.example.testapp.data.product.cache.ProductsCacheDataSource
import com.example.testapp.data.product.cloud.ProductsCloudDataSource
import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.data.product.mappers.ProductDtoToCacheMapper
import com.example.testapp.domain.product.ProductDomain
import com.example.testapp.domain.product.ProductsRepository

class BaseProductsRepository(
    private val productsCacheDataSource: ProductsCacheDataSource,
    private val productsCloudDataSource: ProductsCloudDataSource,
    private val mapperToCache: ProductDtoToCacheMapper,
    private val mapperToDomain: ProductCacheToDomainMapper
): ProductsRepository {
    override suspend fun fetchProducts(): List<ProductDomain> {
        val productsDto = productsCloudDataSource.fetchProducts()
        val productsCache = productsDto.map { it.map(mapperToCache) }
        productsCacheDataSource.saveProducts(productsCache)

        return productsCacheDataSource.fetchProducts().map { it.map(mapperToDomain) }
    }

    override suspend fun fetchProduct(id: Int): ProductDomain {
        return productsCacheDataSource.fetchProduct(id).map(mapperToDomain)
    }
}