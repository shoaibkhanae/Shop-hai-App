package com.example.shophai.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shophai.data.api.response.login.request.LoginRequest
import com.example.shophai.data.api.response.login.response.LoginResponse
import com.example.shophai.data.repository.UserRepository
import com.example.shophai.utils.Response
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var username: String = ""
    var password: String = ""

    val loginResult: LiveData<Response<LoginResponse>> = repository.loginResult

    fun loginUser() {
        val loginRequest = LoginRequest(username, password)
        viewModelScope.launch {
            repository.loginUser(loginRequest)
        }
    }
}