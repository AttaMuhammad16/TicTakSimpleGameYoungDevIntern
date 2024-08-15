package com.ticktakto12.mytictaksimplegame1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        window.statusBarColor=ContextCompat.getColor(this@SplashScreen,R.color.white)
        Handler().postDelayed({
           startActivity(Intent(this@SplashScreen,MyGameActivity::class.java))
            finish()
        },4000)

    }
}