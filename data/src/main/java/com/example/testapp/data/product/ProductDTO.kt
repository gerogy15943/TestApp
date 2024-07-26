package com.example.testapp.data.product

import com.example.testapp.domain.core.Mapper
import com.example.testapp.domain.product.ProductDataToDomainMapper
import com.example.testapp.domain.product.ProductDomain

class ProductDTO(val id: Int,
                 val title: String,
                 val price: Double,
                 val desc: String,
                 val category: String,
                 val image: String,
                 val rating: RatingDomain
): Mapper<ProductDomain, ProductDataToDomainMapper> {
    override fun map(mapper: ProductDataToDomainMapper): ProductDomain {
        return mapper.map(id, title, price, desc, category, image, rating.rate )
    }

    class RatingDomain(val rate: Double, val count: Int)
}