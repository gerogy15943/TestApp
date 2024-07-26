package com.example.testapp.presentation.core

import kotlinx.coroutines.CoroutineDispatcher

interface DispatchersList {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher

    class Base(): DispatchersList{
        override fun io(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.IO
        override fun main(): CoroutineDispatcher = kotlinx.coroutines.Dispatchers.Main
    }
}