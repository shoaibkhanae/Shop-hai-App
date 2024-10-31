package com.example.shophai.data.api

import com.example.shophai.data.model.login.request.LoginRequest
import com.example.shophai.data.model.login.response.LoginResponse
import com.example.shophai.data.model.products.Products
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShopApiService {
    @GET("products")
    suspend fun getProducts(): Response<Products>

    @GET("products/category/{category}")
    suspend fun getCategoryProducts(@Path(value = "category") category: String): Response<Products>

    @POST("auth/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}