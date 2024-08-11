package com.example.shophai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.model.Products
import com.example.shophai.data.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository): ViewModel() {
    val products: LiveData<Products> = repository.products

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()
        }
    }
}