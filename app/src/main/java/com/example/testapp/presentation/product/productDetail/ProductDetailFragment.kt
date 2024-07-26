package com.example.testapp.presentation.product.productDetail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.navigation.navGraphViewModels
import com.example.testapp.databinding.FragmentProductDetailBinding
import com.example.testapp.presentation.core.BaseFragment
import com.example.testapp.presentation.product.productsList.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment: BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding>() {
    override fun viewModelClass(): Class<ProductDetailViewModel> = ProductDetailViewModel::class.java
    override fun viewBinding(): FragmentProductDetailBinding = FragmentProductDetailBinding.inflate(layoutInflater)
    private val args by navArgs<ProductDetailFragmentArgs>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProduct(args.productId)
        viewModel.observeProduct(this, Observer {
            it.show(binding)
        })
    }
}