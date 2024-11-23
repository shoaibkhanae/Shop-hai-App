package com.example.shophai.data.api

import com.example.shophai.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    fun getInstance(): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}