package com.example.testapp.presentation.product.productsList

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.example.testapp.R
import com.example.testapp.databinding.FragmentProductDetailBinding
import com.example.testapp.presentation.core.ImageFromUrl
import com.example.testapp.presentation.product.productDetail.IdMapper

abstract class ProductUi: ShowProduct, GetId{

    override fun show(titleTv: TextMapper, categoryTv: TextMapper, priceTv: TextMapper,
        ratingTv: TextMapper, imageFromUrl: ImageFromUrl) = Unit

    override fun show(binding: FragmentProductDetailBinding) = Unit

    override fun show(errorTv: TextView) = Unit
    override fun id(idMapper: IdMapper) = Unit
    override fun show(progressBar: ProgressBar) = Unit

    object Loading: ProductUi(){

        override fun show(binding: FragmentProductDetailBinding) {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    data class Base(private val id: Int, private val title: String, private val price: Double, private val desc: String,
        private val category: String, private val image: String, private val rate: Double
    ): ProductUi() {
        override fun show(titleTv: TextMapper, categoryTv: TextMapper, priceTv: TextMapper,
                          ratingTv: TextMapper, imageFromUrl: ImageFromUrl) {
            titleTv.map(R.string.product_title, title)
            categoryTv.map(R.string.product_category, category)
            priceTv.map(R.string.product_price, price.toString())
            ratingTv.map(R.string.product_rating, rate.toString())
            imageFromUrl.loadImage(image)
        }

        override fun show(binding: FragmentProductDetailBinding) {
            binding.progressBar.visibility = View.GONE
            binding.tvProductTitle.map(R.string.product_title, title)
            binding.tvProductDesc.map(R.string.product_desc, desc)
            binding.tvProductCategory.map(R.string.product_category, category)
            binding.tvProductPrice.map(R.string.product_price, price.toString())
            binding.tvProductRate.map(R.string.product_rating, rate.toString())
            binding.imgProduct.loadImage(image)
        }
        override fun id(idMapper: IdMapper) { idMapper.mapId(id) }

    }

    data class Error(private val error: String): ProductUi() {
        override fun show(errorTv: TextView) { errorTv.text = error }
        override fun show(binding: FragmentProductDetailBinding) {
            binding.progressBar.visibility = View.GONE
            binding.llError.visibility = View.VISIBLE
            binding.tvError.text = error
        }
    }
}