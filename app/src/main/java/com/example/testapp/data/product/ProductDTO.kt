package com.example.testapp.data.product

import com.example.testapp.core.Mapper
import com.example.testapp.data.product.cache.ProductCache
import com.example.testapp.data.product.mappers.ProductDtoToCacheMapper

data class ProductDTO(val id: Int,
                 val title: String,
                 val price: Double,
                 val description: String,
                 val category: String,
                 val image: String,
                 val rating: Rating): Mapper<ProductDtoToCacheMapper, ProductCache> {

    data class Rating(val rate: Double, val count: Int)

    override fun map(mapper: ProductDtoToCacheMapper): ProductCache {
        return mapper.map(id, title, price, description, category, image, rating)
    }
}