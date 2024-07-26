package com.example.testapp.presentation.product.productsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.testapp.databinding.RvProductErrorBinding
import com.example.testapp.databinding.RvProductLayoutBinding
import com.example.testapp.databinding.RvProductLoaderBinding
import com.example.testapp.presentation.product.productDetail.IdMapper

class ProductsAdapter(private val data: List<ProductUi>, private val retryLoad: RetryLoad,
                      private val mapper: IdMapper): RecyclerView.Adapter<ProductsAdapter.AbstractViewHolder>() {

    class LoadingViewHolder(private val binding: RvProductLoaderBinding): AbstractViewHolder(binding){
        override fun bind(product: ProductUi) = Unit
    }

    class ProductViewHolder(private val mapper: IdMapper, private val binding: RvProductLayoutBinding): AbstractViewHolder(binding) {
        override fun bind(product: ProductUi) {
            product.show(binding.tvProductTitle, binding.tvProductCategory, binding.tvProductPrice,
                binding.tvProductRate, binding.imgProduct)

            binding.clProduct.setOnClickListener { product.id(mapper) }
        }
    }

    class ErrorViewHolder(private val retryLoad: RetryLoad, private val binding: RvProductErrorBinding): AbstractViewHolder(binding) {
        override fun bind(product: ProductUi) {
            product.show(binding.tvProductError)
            binding.btnProductRetry.setOnClickListener { retryLoad.click() }
        }
    }

    abstract class AbstractViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root){
        abstract fun bind(product: ProductUi)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
         return when(viewType){
            0 -> LoadingViewHolder(RvProductLoaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> ProductViewHolder(mapper, RvProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
             else -> ErrorViewHolder(retryLoad, RvProductErrorBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun onBindViewHolder(holder: AbstractViewHolder, position: Int) {
       holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return when(data[position]){
            is ProductUi.Loading -> 0
            is ProductUi.Base -> 1
            is ProductUi.Error -> 2
            else -> -1
        }
    }
}

interface RetryLoad{
    fun click()
}

interface GetId{
    fun id(idMapper: IdMapper)
}
