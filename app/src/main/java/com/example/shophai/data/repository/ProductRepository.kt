package com.example.shophai.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.api.response.products.Products
import com.example.shophai.data.local.ProductDao
import com.example.shophai.utils.Response
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ShopApiService,
    private val productDao: ProductDao
) {

    private val _products = MutableLiveData<Response<Products>>()
    val products: LiveData<Response<Products>> = _products

    suspend fun getProducts() {
        _products.postValue(Response.Loading())
        try {
            val result = apiService.getProducts()
            if (result.body() != null) {
                _products.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _products.postValue(e.message?.let { Response.Error(it) })
        }
    }


    suspend fun getCategoryProducts(category: String) {
        _products.postValue(Response.Loading())
        try {
            val result = apiService.getCategoryProducts(category)
            if (result.body() != null) {
                _products.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            _products.postValue(e.message?.let { Response.Error(it) })
        }
    }
}