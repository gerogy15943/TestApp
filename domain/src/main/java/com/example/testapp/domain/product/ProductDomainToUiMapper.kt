package com.example.testapp.domain.product

import com.example.testapp.presentation.core.Mapper2


interface ProductDomainToUiMapper: Mapper2 {
    fun map(id: Int, title: String, price: Double, desc: String, category: String, image: String,
            rate: Double)
    fun mapError(error: String)

    class BaseProductDomainToUiMapper: ProductDomainToUiMapper {
        override fun map(id: Int, title: String, price: Double, desc: String, category: String,
            image: String,
            rate: Double
        ): ProductUi {
            return ProductUi.Base(id, title, price, desc, category, image, rate)
        }

        override fun mapError(error: String): ProductUi {
            return ProductUi.Error()
        }
    }
}