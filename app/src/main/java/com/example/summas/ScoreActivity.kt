package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        scoreTextView.text = AppState.highScore.value.toString()
    }
}