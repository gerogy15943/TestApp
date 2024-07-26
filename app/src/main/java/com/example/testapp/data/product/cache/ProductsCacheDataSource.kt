package com.example.testapp.data.product.cache

import android.content.res.Resources.NotFoundException
import com.example.testapp.core.room.Database

interface ProductsCacheDataSource {
    fun fetchProducts(): List<ProductCache>
    fun fetchProduct(id: Int): ProductCache
    fun saveProducts(products: List<ProductCache>)

    class BaseProductsCacheDataSource(private val database: Database): ProductsCacheDataSource{
        override fun fetchProducts(): List<ProductCache> {
            return database.productsDao().fetchProducts()
        }

        override fun fetchProduct(id: Int): ProductCache {
            return database.productsDao().fetchProduct(id) ?: throw NotFoundException()
        }

        override fun saveProducts(products: List<ProductCache>) {
            database.productsDao().insertProducts(products)
        }
    }
}
