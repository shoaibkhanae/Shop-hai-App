package com.example.shophai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.model.products.Products
import com.example.shophai.data.model.products.ProductsItem
import com.example.shophai.data.repository.ProductRepository
import com.example.shophai.utils.Response
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ProductRepository): ViewModel() {
    private val _selected = MutableLiveData<ProductsItem>()
    val selected: LiveData<ProductsItem> = _selected

    val products: LiveData<Response<Products>> = repository.products

    init {
        getProducts()
    }

    fun select(productsItem: ProductsItem) {
        _selected.value = productsItem
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