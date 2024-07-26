package com.example.testapp.presentation.product.productsList

import androidx.annotation.StringRes

interface TextMapper {
    fun map(@StringRes resId: Int, setText: String)
}