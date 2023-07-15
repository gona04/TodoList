package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getSupportActionBar()?.hide()

        val i = Intent(this@SplashActivity, MainActivity::class.java)
        Handler().postDelayed(Runnable {
            startActivity(i)
            finish()
        }, 600)

    }
}