package com.example.spotitout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button

class MenuPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_page)

        val button: Button = findViewById(R.id.newbtn)

        button.setOnClickListener {
            startActivity(Intent(this, AllGames::class.java))
        }

        val button3: Button = findViewById(R.id.button3)

        button3.setOnClickListener {
            startActivity(Intent(this, Highscore::class.java))
        }
    }
}