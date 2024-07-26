package com.example.testapp.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface LiveDataWrapper<T> {
    fun map(data: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)
}