package com.example.testapp.presentation.product.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.StringRes
import com.example.testapp.presentation.product.productsList.TextMapper

class CustomTextView: androidx.appcompat.widget.AppCompatTextView, TextMapper {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun map(@StringRes resId: Int, setText: String){
        text = context.getString(resId, setText)
    }
}