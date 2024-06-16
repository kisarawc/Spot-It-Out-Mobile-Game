package com.example.spotitout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TimeEnd : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_end)

        val score = intent.getIntExtra("SCORE", 0)
        val scoreTextView = findViewById<TextView>(R.id.textView3)
        scoreTextView.text = "Score: $score"

        val trybtn: Button = findViewById(R.id.trybtn)

        trybtn.setOnClickListener {
            startActivity(Intent(this, Picture01::class.java))
        }

        val button4: Button = findViewById(R.id.button4)

        button4.setOnClickListener {
            startActivity(Intent(this, Highscore::class.java))
        }

        val timeTakenSeconds = intent.getLongExtra("TIME_TAKEN_SECONDS", 0)

        val time = findViewById<TextView>(R.id.takentime)
        time.text = "Time taken: $timeTakenSeconds s"
    }
}