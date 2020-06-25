package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class HelpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)


        val versionText = findViewById<TextView>(R.id.versionTextView)
        val versionCode = BuildConfig.VERSION_CODE;
        versionText.text = "Version " + versionCode.toString()
    }
}