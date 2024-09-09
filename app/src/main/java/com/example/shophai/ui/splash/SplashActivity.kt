package com.example.shophai.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.shophai.databinding.ActivitySplashBinding
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
        goToNextScreen()
    }

    private fun goToNextScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeScreen()
            finish()
        },3000)
    }

    private fun goToHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}