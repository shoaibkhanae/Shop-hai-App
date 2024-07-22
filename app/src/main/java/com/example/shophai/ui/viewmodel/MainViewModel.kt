package com.example.shophai.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.api.RetrofitBuilder
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.model.Products
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _products = MutableLiveData<Products>()
    val products: LiveData<Products> = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val api = RetrofitBuilder.getInstance().create(ShopApiService::class.java)
            try {
                val result = api.getProducts()
                if (result.body() != null) {
                    _products.postValue(result.body())
                }
            } catch (e: Exception) {
                Log.d("Response","$e")
            }
        }
    }
}