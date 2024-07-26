package com.example.testapp.presentation.product.productsList

import android.widget.ProgressBar
import android.widget.TextView
import com.example.testapp.databinding.FragmentProductDetailBinding
import com.example.testapp.databinding.RvProductLayoutBinding
import com.example.testapp.presentation.core.ImageFromUrl

interface ShowProduct {
    fun show(titleTv: TextMapper, categoryTv: TextMapper, priceTv: TextMapper, ratingTv: TextMapper,
             imageFromUrl: ImageFromUrl)
    fun show(binding: FragmentProductDetailBinding)
    fun show(errorTv: TextView)
    fun show(progressBar: ProgressBar)
}