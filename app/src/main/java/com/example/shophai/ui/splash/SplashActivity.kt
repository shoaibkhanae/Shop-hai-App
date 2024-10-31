package com.example.shophai.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.shophai.R
import com.example.shophai.ui.auth.AuthActivity
import com.example.shophai.ui.home.MainActivity
import com.example.shophai.utils.SessionManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_splash)
        splashScreen.setKeepOnScreenCondition{ true }
        init()
    }

    private fun init() {
        splashScreenLogic()
    }

    private fun splashScreenLogic() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (getAuthToken() != null) {
                goToHomeScreen()
            } else {
                goToLoginScreen()
            }
            finish()
        },3000)
    }

    private fun getAuthToken(): String? {
        return SessionManager.getToken(this)
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