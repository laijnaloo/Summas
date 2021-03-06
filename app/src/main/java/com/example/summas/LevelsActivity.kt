package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)
        renderLevels()
    }

    override fun onResume() {
        super.onResume()
        renderLevels()
    }

    fun renderLevels() {
        AppState.levelPlayedNow.value = 0

        val easyButton = findViewById<Button>(R.id.easyButton)
        easyButton.setOnClickListener {

            AppState.levelPlayedNow.value = 1
            val intentEasyButton = Intent(this, PlayActivity::class.java);
            startActivity(intentEasyButton)
        }

        val mediumButton = findViewById<Button>(R.id.mediumButton)
        var mediumColor = getResources().getColor(R.color.colorGrey)
        var mediumImage = getResources().getDrawable(R.drawable.lock)

        if(AppState.highScore.value!! >= 5) {
            mediumColor = getResources().getColor(R.color.colorGreen)
            mediumImage = getResources().getDrawable(R.drawable.student)
        }
        mediumButton.setBackgroundColor(mediumColor)
        mediumButton.setCompoundDrawablesWithIntrinsicBounds(null, null, mediumImage, null)
        mediumButton.setOnClickListener {
            if(AppState.highScore.value!! >= 5){
                AppState.levelPlayedNow.value = 2
                val intentMediumButton = Intent(this, PlayActivity::class.java);
                startActivity(intentMediumButton)
            }
        }

        val hardButton = findViewById<Button>(R.id.hardButton)
        var hardColor = getResources().getColor(R.color.colorGrey)
        var hardImage = getResources().getDrawable(R.drawable.lock)
        if(AppState.highScore.value!! >= 10) {
            hardColor = getResources().getColor(R.color.colorRed)
            hardImage = getResources().getDrawable(R.drawable.sensei)
        }
        hardButton.setBackgroundColor(hardColor)
        hardButton.setCompoundDrawablesWithIntrinsicBounds(null, null, hardImage, null)
        hardButton.setOnClickListener {
            if(AppState.highScore.value!! >= 10) {
                AppState.levelPlayedNow.value = 3
                val intentHardButton = Intent(this, PlayActivity::class.java);
                startActivity(intentHardButton)
            }
        }

    }

}