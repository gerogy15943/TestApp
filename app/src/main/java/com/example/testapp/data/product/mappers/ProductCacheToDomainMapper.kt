package com.example.testapp.data.product.mappers

import com.example.testapp.domain.product.ProductDomain

interface ProductCacheToDomainMapper {
    fun map(id: Int, title: String, price: Double, desc: String, category: String,
            image: String, rate: Double): ProductDomain

    class BaseProductCacheToDomainMapper: ProductCacheToDomainMapper{
        override fun map(id: Int, title: String, price: Double, desc: String, category: String,
            image: String, rate: Double
        ): ProductDomain {
            return ProductDomain.Success(id, title, price, desc, category, image, rate)
        }
    }
}