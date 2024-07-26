package com.example.testapp.domain.product

import com.example.testapp.core.Mapper
import com.example.testapp.presentation.product.productDetail.IdMapper
import com.example.testapp.presentation.product.productsList.GetId
import com.example.testapp.presentation.product.productsList.ProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductUi

interface ProductDomain: Mapper<ProductToUiMapper, ProductUi>, GetId {
    data class Success(private val id: Int, private val title: String, private val price: Double, private val desc: String,
                  private val category: String, private val image: String, private val rate: Double): ProductDomain {
        override fun map(mapper: ProductToUiMapper): ProductUi {
            return mapper.map(id, title, price, desc, category, image, rate)
        }

        override fun id(idMapper: IdMapper) {
            idMapper.mapId(id)
        }
    }

    data class Error(private val error: Throwable): ProductDomain {
        override fun map(mapper: ProductToUiMapper): ProductUi {
           return mapper.map(error)
        }

        override fun id(idMapper: IdMapper) = Unit
    }
}