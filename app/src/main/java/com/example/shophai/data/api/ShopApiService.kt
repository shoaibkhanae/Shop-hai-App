package com.example.shophai.data.api

import com.example.shophai.data.model.products
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fakestoreapi.com/"


private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface ShopApiService {
    @GET("products")
    suspend fun getAllProducts(): Response<products>
}

object ShopApi {
    val retrofitService: ShopApiService by lazy {
        retrofit.create(ShopApiService::class.java)
    }
}