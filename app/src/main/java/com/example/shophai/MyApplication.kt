package com.example.shophai

import android.app.Application
import com.example.shophai.data.api.RetrofitBuilder
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.repository.UserRepository
import com.example.shophai.data.repository.ProductRepository

class MyApplication : Application() {
    val api by lazy { RetrofitBuilder.getInstance().create(ShopApiService::class.java) }
    val repository by lazy { ProductRepository(api) }
    val loginRepository by lazy { UserRepository(api) }
}