package com.example.shophai.data.api

import com.example.shophai.data.model.Products
import retrofit2.Response
import retrofit2.http.GET

interface ShopApiService {
    @GET("products")
    suspend fun getProducts(): Response<Products>
}