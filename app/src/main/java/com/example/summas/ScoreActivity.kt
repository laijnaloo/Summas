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

        val currentLevelTextView = findViewById<TextView>(R.id.currentLevel)

        var currentLevel = "Easy"

        if(AppState.highScore.value!! >= 10){
            currentLevel = "Sensei"

        } else if (AppState.highScore.value!! >= 5){
            currentLevel = "Semi Experienced"
        }
        currentLevelTextView.text = currentLevel
    }
}