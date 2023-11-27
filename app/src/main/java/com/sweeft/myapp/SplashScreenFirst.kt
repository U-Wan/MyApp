package com.sweeft.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenFirst : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_first)



        Handler().postDelayed({
            startActivity(Intent(this, SplashScreenSecond::class.java))
            finish()
        }, 2000)

    }
}