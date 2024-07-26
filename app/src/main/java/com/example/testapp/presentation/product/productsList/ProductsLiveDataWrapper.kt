package com.example.testapp.presentation.product.productsList

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.testapp.presentation.core.LiveDataWrapper

class ProductsLiveDataWrapper: LiveDataWrapper<List<ProductUi>>{
    private val liveData = MutableLiveData<List<ProductUi>>()

    override fun map(data: List<ProductUi>) {
        liveData.postValue(data)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<ProductUi>>) {
        liveData.observe(owner, observer)
    }
}
