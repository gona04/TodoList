package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

/*
* This activity is for the splash screen of the app.
* */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide the action bar
        supportActionBar?.hide()

        // Create an intent to navigate to the MainActivity
        val i = Intent(this@SplashActivity, MainActivity::class.java)

        // Delay the intent start using a Handler for 600 milliseconds (0.6 seconds)
        Handler().postDelayed(Runnable {
            startActivity(i)
            finish()
        }, 600)
    }
}