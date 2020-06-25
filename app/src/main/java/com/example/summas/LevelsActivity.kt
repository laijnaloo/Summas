package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LevelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_levels)

        val easyButton = findViewById<Button>(R.id.easyButton)
        easyButton.setOnClickListener {
            val intentEasyButton = Intent(this, LevelsActivity::class.java);
            startActivity(intentEasyButton)
        }

    }
}