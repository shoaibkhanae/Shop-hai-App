package com.example.shophai.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.shophai.databinding.ActivitySplashBinding
import com.example.shophai.ui.auth.AuthActivity
import com.example.shophai.ui.home.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        splashScreenLogic()
    }

    private fun splashScreenLogic() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToLoginScreen()
            finish()
        },3000)
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
    }

    private fun goToHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}