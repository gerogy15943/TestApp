package com.example.testapp.presentation.product.productsList

import android.content.res.Resources.NotFoundException
import com.example.testapp.R
import com.example.testapp.domain.core.NotFound
import com.example.testapp.domain.core.UnknownHost
import com.example.testapp.presentation.core.ResourceManager


class BaseProductToUiMapper(private val resourceManager: ResourceManager): ProductToUiMapper {
    override fun map(id: Int, title: String, price: Double, desc: String, category: String,
        image: String, rate: Double): ProductUi {
            return ProductUi.Base(id, title, price, desc, category, image, rate)
        }

    override fun map(error: Throwable): ProductUi {
        return when(error){
            is UnknownHost -> ProductUi.Error(resourceManager.fetchString(R.string.unknown_host_error))
            is NotFound -> ProductUi.Error(resourceManager.fetchString(R.string.not_found_error))
            else -> ProductUi.Error(resourceManager.fetchString(R.string.generic_error))
        }
    }
}

interface ProductToUiMapper {
    fun map(id: Int, title: String, price: Double, desc: String,
            category: String, image: String, rate: Double): ProductUi
    fun map(error: Throwable): ProductUi
}