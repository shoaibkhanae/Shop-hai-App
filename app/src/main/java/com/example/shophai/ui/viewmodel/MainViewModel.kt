package com.example.shophai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.model.products.Products
import com.example.shophai.data.repository.ProductRepository
import com.example.shophai.utils.Response
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository): ViewModel() {
    val products: LiveData<Response<Products>> = repository.products


    init {
        getProducts()
    }

   fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()
        }
    }

    fun getCategoryProducts(category: String) {
        viewModelScope.launch {
            repository.getCategoryProducts(category)
        }
    }

}