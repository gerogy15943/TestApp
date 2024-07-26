package com.example.testapp.domain.product

import com.example.testapp.domain.core.Mapper2

interface ProductDataToDomainMapper: Mapper2 {
    fun map(id: Int, title: String, price: Double, desc: String, category: String, image: String, rate: Double): ProductDomain

}