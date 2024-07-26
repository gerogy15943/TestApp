package com.example.testapp.data.product.mappers

import com.example.testapp.data.product.ProductDTO
import com.example.testapp.data.product.cache.ProductCache

interface ProductDtoToCacheMapper {
    fun map(id: Int,
            title: String, price: Double, desc: String,
            category: String, image: String, rating: ProductDTO.Rating): ProductCache

    class BaseProductDtoToCacheMapper: ProductDtoToCacheMapper{
        override fun map(
            id: Int,
            title: String,
            price: Double,
            desc: String,
            category: String,
            image: String,
            rating: ProductDTO.Rating
        ): ProductCache {
            return ProductCache(id, title, price, desc, category, image, rating.rate)
        }
    }
}