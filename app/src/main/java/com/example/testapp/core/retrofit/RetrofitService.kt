package com.example.testapp.core.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitService {
    fun <T> fetchService(clazz: Class<T>):T

    class BaseRetrofitService(private val baseUrl: String): RetrofitService {
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        override fun <T> fetchService(clazz: Class<T>): T {
            return retrofit.create(clazz)
        }
    }
}