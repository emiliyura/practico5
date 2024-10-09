package com.example.kotlin_5pract.api

import com.example.kotlin_5pract.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/{id}")
    suspend fun getProductById (@Path("id") id: Int): Product
}