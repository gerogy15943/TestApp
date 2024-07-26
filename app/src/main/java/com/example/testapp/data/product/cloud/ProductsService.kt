package com.example.testapp.data.product.cloud

import com.example.testapp.data.product.ProductDTO
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsService {
    @GET("/products")
    fun fetchProducts(): Call<List<ProductDTO>>
}