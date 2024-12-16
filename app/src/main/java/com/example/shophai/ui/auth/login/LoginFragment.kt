package com.example.shophai.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.shophai.MyApplication
import com.example.shophai.databinding.FragmentLoginBinding
import com.example.shophai.ui.auth.validator.EmptyValidator
import com.example.shophai.ui.auth.validator.PasswordValidator
import com.example.shophai.ui.auth.validator.base.BaseValidator
import com.example.shophai.ui.home.MainActivity
import com.example.shophai.utils.Response
import com.example.shophai.utils.SessionManager

class LoginFragment : Fragment() {
    private var _binidng: FragmentLoginBinding? = null
    val binding
        get() = _binidng!!

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory((requireActivity().application as MyApplication).loginRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binidng = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.btnSignIn.setOnClickListener { signIn() }
    }

    private fun signIn() {
        validateUsername()
        validatePassword()

        if (binding.etUsername.error == null &&
            binding.etPassword.error == null
            ) {
            signInWithApi()
        }
    }

    private fun signInWithApi() {
       loginViewModel.loginUser()

        loginViewModel.loginResult.observe(viewLifecycleOwner) {
            when(it) {
                is Response.Loading -> {
                    showProgressbar()
                }
                is Response.Success -> {
                    stopProgressbar()
                    val token  = it?.data?.token
                    if (token != null) {
                        saveAuthToken(token)
                        showToast("Login Successfully")
                        goToHomeScreen()
                    }
                }
                is Response.Error -> {
                    stopProgressbar()
                    showToast(it.error.toString())
                }
            }
        }
    }

    private fun saveAuthToken(token: String) {
        SessionManager.saveAuthToken(requireContext(), token)
    }

    private fun showProgressbar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun stopProgressbar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun goToHomeScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun validateUsername() {
        loginViewModel.username = binding.etUsername.text.toString()

        val usernameValidation = BaseValidator.validate(
            EmptyValidator(loginViewModel.username)
        )
        binding.etUsername.error =
            if (!usernameValidation.isSuccess) getString(usernameValidation.message) else null
    }

    private fun validatePassword() {
        loginViewModel.password = binding.etPassword.text.toString()

        val passwordValidation = BaseValidator.validate(
            EmptyValidator(loginViewModel.password),
            PasswordValidator(loginViewModel.password)
        )
        binding.etPassword.error =
            if (!passwordValidation.isSuccess) getString(passwordValidation.message) else null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binidng = null
    }
}