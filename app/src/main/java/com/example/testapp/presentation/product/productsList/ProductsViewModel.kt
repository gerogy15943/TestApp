package com.example.testapp.presentation.product.productsList

import androidx.lifecycle.*
import com.example.testapp.domain.product.ProductInteractor
import com.example.testapp.presentation.core.DispatchersList
import com.example.testapp.presentation.core.LiveDataWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsLiveData: LiveDataWrapper<List<ProductUi>>,
    private val productInteractor: ProductInteractor,
    private val mapper: ProductToUiMapper,
    private val dispathers: DispatchersList
) : ViewModel() {

    fun fetchProducts() {
        productsLiveData.map(listOf(ProductUi.Loading))
        viewModelScope.launch(dispathers.io()) {
            val productsDomain = productInteractor.fetchProducts()
            val productsUi = productsDomain.map { it.map(mapper) }
            productsLiveData.map(productsUi)
        }
    }

    fun observeProducts(owner: LifecycleOwner, observer: Observer<List<ProductUi>>) {
        productsLiveData.observe(owner, observer)
    }
}