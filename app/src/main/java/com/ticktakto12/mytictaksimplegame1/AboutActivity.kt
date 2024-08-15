package com.ticktakto12.mytictaksimplegame1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
       window.statusBarColor=ContextCompat.getColor(this@AboutActivity,R.color._9c27b0)

    }
}