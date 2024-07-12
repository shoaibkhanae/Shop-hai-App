package com.example.shophai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.api.ShopApi
import com.example.shophai.data.model.products
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _products = MutableLiveData<products>()
    val products: LiveData<products> = _products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            val results = ShopApi.retrofitService.getAllProducts()
            _products.value = results.body()
        }
    }
}