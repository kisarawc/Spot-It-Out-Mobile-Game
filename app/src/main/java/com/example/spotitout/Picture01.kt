package com.example.spotitout

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject

class Picture01 : AppCompatActivity() {
    private var score = 0
    private lateinit var countDownTimer: CountDownTimer
    private val totalTimeInMillis: Long = 60000
    private var differencesFound = 0
    private val totalDifferences = 3
    private lateinit var sharedPreferences: SharedPreferences
    private var currentTimeInMillis: Long = 0
    private var endTimeInMillis: Long = 0
    private var totTimeInMillis: Long = 0
    private var gameEndedByUser = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture1)

        sharedPreferences = getSharedPreferences("high_score", Context.MODE_PRIVATE)
        val ans1 = findViewById<ImageView>(R.id.ans1)
        val ans2 = findViewById<ImageView>(R.id.ans2)
        val ans3 = findViewById<ImageView>(R.id.ans3)
        val ans4 = findViewById<ImageView>(R.id.ans4)
        val ans5 = findViewById<ImageView>(R.id.ans5)
        val ans6 = findViewById<ImageView>(R.id.ans6)
        val btn1 = findViewById<Button>(R.id.finishbtn)

        setClickListener(ans1, ans2)
        setClickListener(ans3, ans4)
        setClickListener(ans5, ans6)

        startCountdownTimer()

        btn1.setOnClickListener {
            navigateToTimeEnd()
        }
    }

    private fun navigateToTimeEnd() {
        endTimeInMillis = System.currentTimeMillis()
        totTimeInMillis = endTimeInMillis - currentTimeInMillis
        val seconds = totTimeInMillis / 1000 // Convert milliseconds to seconds
        val message = "Time taken: $seconds seconds"
        Toast.makeText(this@Picture01, message, Toast.LENGTH_SHORT).show()

        gameEndedByUser = true
        val intent = Intent(this@Picture01, TimeEnd::class.java)
        intent.putExtra("SCORE", score)
        intent.putExtra("TIME_TAKEN_SECONDS", seconds)
        startActivity(intent)
        finish()

        val scoresJsonArray = sharedPreferences.getString("HIGH_SCORES", "[]")
        val scoresList = JSONArray(scoresJsonArray)

        val scoreObject = JSONObject()
        scoreObject.put("score", score)
        scoreObject.put("time", seconds)
        scoresList.put(scoreObject)

        with(sharedPreferences.edit()) {
            putString("HIGH_SCORES", scoresList.toString())
            apply()
        }

    }

    private fun startCountdownTimer() {
        currentTimeInMillis = System.currentTimeMillis()
        countDownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val timeRemaining = millisUntilFinished / 1000
                updateTimerText(timeRemaining)
            }

            override fun onFinish() {
                if (!gameEndedByUser) {
                    navigateToTimeEnd()
                }
            }
        }
        countDownTimer.start()
    }

    private fun updateTimerText(timeRemaining: Long) {
        val timerTextView = findViewById<TextView>(R.id.timerText)
        timerTextView.text = "$timeRemaining s"
    }

    private fun setClickListener(image1: ImageView, image2: ImageView) {
        image1.setOnClickListener {
            if (!isDifferenceFound(image1)) {
                differencesFound++
                checkForAllDifferencesFound()
            }

            if (image1.drawable.constantState != resources.getDrawable(R.drawable.circlestyle3).constantState) {
                score++
                updateScore()
            }

            image1.setImageResource(R.drawable.circlestyle3)
            image2.setImageResource(R.drawable.circlestyle3)
        }

        image2.setOnClickListener {
            if (image1.drawable.constantState != resources.getDrawable(R.drawable.circlestyle3).constantState) {
                score++
                updateScore()
            }

            image1.setImageResource(R.drawable.circlestyle3)
            image2.setImageResource(R.drawable.circlestyle3)
        }
    }

    private fun updateScore() {
        val scoreTextView = findViewById<TextView>(R.id.scoreText)
        scoreTextView.text = "Score: $score/3"
    }

    private fun isDifferenceFound(image: ImageView): Boolean {
        return image.drawable.constantState == resources.getDrawable(R.drawable.circlestyle3).constantState
    }

    private fun checkForAllDifferencesFound() {
        if (differencesFound == totalDifferences) {
            endTimeInMillis = System.currentTimeMillis()
            totTimeInMillis = endTimeInMillis - currentTimeInMillis
            val seconds = totTimeInMillis / 1000
            val score = differencesFound
            val message = "Time taken: $seconds seconds"
            Toast.makeText(this@Picture01, message, Toast.LENGTH_SHORT).show()

            val scoresJsonArray = sharedPreferences.getString("HIGH_SCORES", "[]")
            val scoresList = JSONArray(scoresJsonArray)

            val scoreObject = JSONObject()
            scoreObject.put("score", score)
            scoreObject.put("time", seconds)
            scoresList.put(scoreObject)

            with(sharedPreferences.edit()) {
                putString("HIGH_SCORES", scoresList.toString())
                apply()
            }

            gameEndedByUser = true
            val intent = Intent(this@Picture01, Win::class.java)
            intent.putExtra("TIME_TAKEN_SECONDS", seconds)
            startActivity(intent)
            finish()
        }
    }
}
