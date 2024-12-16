package com.example.shophai.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.api.response.login.response.LoginResponse
import com.example.shophai.data.api.response.login.request.LoginRequest
import com.example.shophai.utils.Response

class UserRepository(val apiService: ShopApiService) {
    private val _loginResult = MutableLiveData<Response<LoginResponse>>()
    val loginResult: LiveData<Response<LoginResponse>> = _loginResult

    suspend fun loginUser(loginRequest: LoginRequest) {
        _loginResult.postValue(Response.Loading())
        try {
            val result = apiService.loginUser(loginRequest)
            if (result.body() != null) {
                _loginResult.postValue(Response.Success(result.body()))
            } else {
                _loginResult.postValue(Response.Error("wrong credential"))
            }
        } catch (e: Exception) {
            Log.d("Response", "${e.message.toString()}")
        }
    }
}