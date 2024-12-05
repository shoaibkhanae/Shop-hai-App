package com.example.shophai.ui.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.shophai.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        enableEdgeToEdge()
    }

    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}