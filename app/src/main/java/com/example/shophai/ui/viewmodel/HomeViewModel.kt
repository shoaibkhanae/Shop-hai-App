package com.example.shophai.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.model.ProductsItem
import com.example.shophai.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    private val _selected = MutableLiveData<ProductsItem>()
    val selected: LiveData<ProductsItem> = _selected

    val cached: LiveData<List<ProductsItem>> = repository.products

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

//    fun getProductsWithCategory(category: String) {
//            cachcedProducts = cached.value!!
//            _products.value = cachcedProducts.filter { it.category == category }
//    }
}