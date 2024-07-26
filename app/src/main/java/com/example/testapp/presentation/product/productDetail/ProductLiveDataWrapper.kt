package com.example.testapp.presentation.product.productDetail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testapp.presentation.core.LiveDataWrapper
import com.example.testapp.presentation.product.productsList.ProductUi

class ProductLiveDataWrapper: LiveDataWrapper<ProductUi> {
    private val liveData = MutableLiveData<ProductUi>()

    override fun map(data: ProductUi) {
        liveData.postValue(data)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<ProductUi>) {
        liveData.observe(owner, observer)
    }
}