package com.example.spotitout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AllGames : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_games)

        val imageView38 = findViewById<ImageView>(R.id.img1)

        imageView38.setOnClickListener {
            val intent = Intent(this, Picture01::class.java)
            startActivity(intent)
        }
    }
}