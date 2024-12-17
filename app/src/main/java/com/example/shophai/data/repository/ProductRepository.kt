package com.example.shophai.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.local.ProductDao
import com.example.shophai.data.model.ProductsItem
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val apiService: ShopApiService,
    private val productDao: ProductDao,
    private val context: Context
) {

    val products: LiveData<List<ProductsItem>> = productDao.getAllProducts().asLiveData()

    suspend fun getProducts() {
        if (isOnline(context)) {
            try {
                val result = apiService.getProducts()
                if (result.body() != null) {
                    val data = result.body()
                    productDao.deleteAllProducts()
                    productDao.insertAll(data!!)
                }
            } catch (e: Exception) {
                Log.d("Response", e.message.toString())
            }
        }
    }



//    suspend fun getCategoryProducts(category: String) {
//        _products.postValue(Response.Loading())
//        try {
//            val result = apiService.getCategoryProducts(category)
//            if (result.body() != null) {
//                _products.postValue(Response.Success(result.body()))
//            }
//        } catch (e: Exception) {
//            _products.postValue(e.message?.let { Response.Error(it) })
//        }
//    }

    private fun isOnline(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }
}