package com.example.testapp.presentation.product.productDetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.domain.product.ProductInteractor
import com.example.testapp.presentation.core.DispatchersList
import com.example.testapp.presentation.core.LiveDataWrapper
import com.example.testapp.presentation.product.productsList.ProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productLiveData: LiveDataWrapper<ProductUi>,
    private val productInteractor: ProductInteractor,
    private val mapper: ProductToUiMapper,
    private val dispathers: DispatchersList
) : ViewModel() {

    fun fetchProduct(id: Int){
        productLiveData.map(ProductUi.Loading)
        viewModelScope.launch(dispathers.io()) {
            val productDomain = productInteractor.fetchProduct(id)
            productLiveData.map(productDomain.map(mapper))
        }
    }

    fun observeProduct(owner: LifecycleOwner, observer: Observer<ProductUi>){
        productLiveData.observe(owner, observer)
    }
}