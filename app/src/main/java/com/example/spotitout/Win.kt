package com.example.spotitout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Win : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val btnNext: Button = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            startActivity(Intent(this, AllGames::class.java))
        }

        val timeTakenSeconds = intent.getLongExtra("TIME_TAKEN_SECONDS", 0)

        val time = findViewById<TextView>(R.id.time)
        time.text = "Time taken: $timeTakenSeconds s"
    }
}