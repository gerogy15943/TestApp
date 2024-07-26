package com.example.testapp.data.product.cache

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testapp.core.Mapper
import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.domain.product.ProductDomain

@Entity
data class ProductCache(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double) : Mapper<ProductCacheToDomainMapper, ProductDomain> {
    override fun map(mapper: ProductCacheToDomainMapper): ProductDomain {
        return mapper.map(id, title, price, description, category, image, rate)
    }
}