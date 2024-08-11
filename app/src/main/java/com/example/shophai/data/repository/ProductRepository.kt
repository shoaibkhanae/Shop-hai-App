package com.example.shophai.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.model.Products

class ProductRepository(private val apiService: ShopApiService) {

    private val _products = MutableLiveData<Products>()
    val products: LiveData<Products> = _products

    suspend fun getProducts() {
        try {
            val result = apiService.getProducts()
            if (result.body() != null) {
                _products.postValue(result.body())
            }
        } catch (e: Exception) {

        }
    }
}