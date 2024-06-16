package com.example.spotitout

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject

class Highscore : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highscore)

        sharedPreferences = getSharedPreferences("high_score", Context.MODE_PRIVATE)

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)

        val scoresJsonArray = sharedPreferences.getString("HIGH_SCORES", "[]")
        val scoresList = JSONArray(scoresJsonArray)

        val sortedScoresList = (0 until scoresList.length())
            .map { scoresList.getJSONObject(it) }
            .sortedWith(compareByDescending<JSONObject> { it.getInt("score") }
                .thenBy { it.getLong("time") })


        for (i in 0 until sortedScoresList.size) {
            val scoreObject = sortedScoresList[i]
            if (scoreObject.has("score")) {
                val rank = i + 1
                val score = scoreObject.getInt("score")
                val time = scoreObject.getLong("time")

                val row = TableRow(this)
                val rankTextView = createTextView(rank.toString(), 25f)

                val scoreText = "$score/3"
                val scoreTextView = createTextView(scoreText, 20f)

                val timeText = "${time}s"
                val timeTextView = createTextView(timeText, 20f)

                row.addView(rankTextView)
                row.addView(scoreTextView)
                row.addView(timeTextView)

                tableLayout.addView(row)
            }
        }
    }

    private fun createTextView(text: String , textSize: Float = 18f): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.textSize = textSize
        return textView
    }
}
