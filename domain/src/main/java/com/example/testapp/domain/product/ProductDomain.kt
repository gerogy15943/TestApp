package com.example.testapp.domain.product

import com.example.testapp.domain.core.Mapper

interface ProductDomain{
        fun map(mapper: Product)
        class Success(
                private val id: Int,
                private val title: String,
                private val price: Double,
                private val desc: String,
                private val category: String,
                private val image: String,
                private val rate: Double
        ): ProductDomain
        class Error(private val error: String): ProductDomain
}
