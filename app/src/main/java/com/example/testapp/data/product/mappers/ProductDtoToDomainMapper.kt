package com.example.testapp.data.product.mappers

import com.example.testapp.data.product.ProductDTO
import com.example.testapp.domain.product.ProductDomain

interface ProductDtoToDomainMapper {
    fun map(id: Int,
            title: String, price: Double, desc: String,
            category: String, image: String, rating: ProductDTO.Rating): ProductDomain

    class BaseProductDtoToDomainMapper: ProductDtoToDomainMapper{
        override fun map(
            id: Int,
            title: String,
            price: Double,
            desc: String,
            category: String,
            image: String,
            rating: ProductDTO.Rating
        ): ProductDomain {
            return ProductDomain.Success(id, title, price, desc, category, image, rating.rate)
        }
    }
}