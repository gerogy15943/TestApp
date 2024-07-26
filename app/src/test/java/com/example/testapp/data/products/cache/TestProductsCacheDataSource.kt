package com.example.testapp.data.products.cache

import android.content.res.Resources.NotFoundException
import com.example.testapp.data.product.ProductDTO
import com.example.testapp.data.product.cache.ProductCache
import com.example.testapp.data.product.cache.ProductsCacheDataSource

class TestProductsCacheDataSource: ProductsCacheDataSource {
    private val productsCache = mutableListOf(
        ProductCache(1, "Продукт 1", 1.00, "Описание 1", "Категория 1", "http://localhost/1", 1.0),
        ProductCache(2, "Продукт 2", 2.00, "Описание 2", "Категория 1", "http://localhost/2", 1.0),
        ProductCache(3, "Продукт 3", 3.00, "Описание 3", "Категория 1", "http://localhost/3", 1.0)
    )
    override fun fetchProducts(): List<ProductCache> {
        return productsCache
    }

    override fun fetchProduct(id: Int): ProductCache {
        return productsCache.find { it.id == id } ?: throw NotFoundException()
    }

    override fun saveProducts(products: List<ProductCache>) {
        productsCache.clear()
        productsCache.addAll(products)
    }
}