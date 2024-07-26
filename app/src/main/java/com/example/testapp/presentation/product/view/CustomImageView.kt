package com.example.testapp.presentation.product.view

import android.content.Context
import android.util.AttributeSet
import com.example.testapp.presentation.core.ImageFromUrl
import com.squareup.picasso.Picasso

class CustomImageView: androidx.appcompat.widget.AppCompatImageView, ImageFromUrl {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun loadImage(url: String) {
        Picasso.get().load(url).into(this)
    }
}