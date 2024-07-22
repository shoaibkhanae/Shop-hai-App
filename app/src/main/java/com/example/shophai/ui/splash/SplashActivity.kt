package com.example.shophai.ui.splash

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
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
        createSplashFullScreen()
        goToNextScreen()
    }

    private fun createSplashFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            WindowManager.LayoutParams.FLAG_FULLSCREEN
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        }
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