package com.example.testapp.presentation.product.productsList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.databinding.FragmentProductsBinding
import com.example.testapp.presentation.core.BaseFragment
import com.example.testapp.presentation.product.productDetail.IdMapper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: BaseFragment<ProductsViewModel, FragmentProductsBinding>() {
    override fun viewModelClass(): Class<ProductsViewModel> = ProductsViewModel::class.java
    override fun viewBinding(): FragmentProductsBinding = FragmentProductsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchProducts()
        viewModel.observeProducts(this, Observer {
            binding.rvProducts.adapter = ProductsAdapter(it, object : RetryLoad {
                override fun click() {
                   viewModel.fetchProducts()
                }
            }, object : IdMapper{
                override fun mapId(id: Int) {
                    findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetail(id))
                }
            })
        })
    }
}