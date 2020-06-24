package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val helpButton = findViewById<Button>(R.id.helpButton)
        helpButton.setOnClickListener {
            val intentHelpButton = Intent(this, HelpActivity::class.java);
            startActivity(intentHelpButton)
        }


    }
}