package com.example.testapp.data.product

import com.example.testapp.domain.product.ProductDataToDomainMapper
import com.example.testapp.domain.product.ProductDomain

class BaseProductDataToDomainMapper: ProductDataToDomainMapper {
    override fun map(
        id: Int,
        title: String,
        price: Double,
        desc: String,
        category: String,
        image: String,
        rate: Double
    ): ProductDomain {
        return ProductDomain.Success(id, title, price, desc, category, image, rate)
    }
}