package com.example.summas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer

class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val constraintLayout = findViewById<ConstraintLayout>(R.id.nextLevelButton)
        val playscreenScoreTextView = findViewById<TextView>(R.id.playscreenScore)
        val answerTextView = findViewById<TextView>(R.id.answerTextView)
        val userInputField = findViewById<EditText>(R.id.userInput)
        val resultsTextView = findViewById<TextView>(R.id.resultText)
        val answerImage = findViewById<ImageView>(R.id.answerImage)
        val playNextLevelButton = findViewById<Button>(R.id.playNextLevelButton)
        val newChallengeButton = findViewById<Button>(R.id.newChallengeButton)
        val restartLevelButton = findViewById<Button>(R.id.restartLevelButton)
        val questionField = findViewById<TextView>(R.id.question)

        val dot1 = findViewById<View>(R.id.dot1)
        val dot2 = findViewById<View>(R.id.dot2)
        val dot3 = findViewById<View>(R.id.dot3)
        val dot4 = findViewById<View>(R.id.dot4)
        val dot5 = findViewById<View>(R.id.dot5)

        if(AppState.levelPlayedNow.value == 1) {
            AppState.score.value = 0
        } else if (AppState.levelPlayedNow.value == 2){
            AppState.score.value = 5
        } else if (AppState.levelPlayedNow.value == 3) {
            AppState.score.value = 10
        }

        var nextLevelIsAbove: Int? = 0
        var currentScoreLevel: Int = 1

        AppState.score.observe(this, Observer { score ->
            playscreenScoreTextView.text = "Score: " + score.toString() })

        fun showCurrentScoreLevel(){

            var listOfDots = listOf(dot1, dot2, dot3, dot4, dot5)

            for ((index, dot) in listOfDots.withIndex()) {

                if(currentScoreLevel -2  >= index  ){
                    dot.setBackgroundResource(R.drawable.circle_dark)
                } else {
                    dot.setBackgroundResource(R.drawable.circle)
                }
            }
        }

        fun playGame(){
            resultsTextView.visibility = View.GONE
            answerImage.visibility = View.GONE
            answerTextView.visibility = View.GONE
            playNextLevelButton.visibility = View.GONE
            newChallengeButton.visibility = View.GONE
            restartLevelButton.visibility = View.GONE
            userInputField.visibility = View.VISIBLE
            userInputField.text.clear()
            var backgroundColor = getResources().getColor(R.color.colorYellow)
            var answer: Int? = null

            showCurrentScoreLevel()

            if(AppState.levelPlayedNow.value == 1) {
                var number1 = (0..10).random()
                var number2 = (0..10).random()
                var question = number1.toString() + " + " + number2.toString() + " = X"
                answer = number1 + number2
                nextLevelIsAbove = 5

                questionField.text = question

            } else if(AppState.levelPlayedNow.value == 2) {
                backgroundColor = getResources().getColor(R.color.colorGreen)
                var number1 = (10..99).random()
                var number2 = (10..99).random()
                var question = number1.toString() + " + " + number2.toString() + " = X"
                answer = number1 + number2
                nextLevelIsAbove = 10


                if (AppState.score.value!! == 5) {
                    currentScoreLevel = 1
                    showCurrentScoreLevel()
                }

                questionField.text = question
            }  else if (AppState.levelPlayedNow.value == 3){
                backgroundColor = getResources().getColor(R.color.colorRed)
                var number1 = (0..1000).random()
                var number2 = (0..1000).random()
                var question = number1.toString() + " + " + number2.toString() + " = X"
                answer = number1 + number2

                if (AppState.score.value!! == 10) {
                    currentScoreLevel = 1
                    showCurrentScoreLevel()
                }

                questionField.text = question
            }

            playscreenScoreTextView.text =  "Score: " + AppState.score.value.toString()
            constraintLayout.setBackgroundColor(backgroundColor)

            fun rightAnswer(){
                resultsTextView.visibility = View.VISIBLE
                resultsTextView.text = "Yay, right answer! +1 point"
                answerImage.visibility = View.VISIBLE
                answerImage.setImageResource(R.drawable.happy_avatar_front);

                hideKeyboard()

                answerTextView.visibility = View.VISIBLE
                answerTextView.text = userInputField.text.toString()
                userInputField.visibility = View.GONE
                AppState.score.value = AppState.score.value?.plus(1)
            }

            userInputField.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    if (AppState.score.value == 14 && userInputField.text.toString() == answer.toString()){
                        rightAnswer()
                        playNextLevelButton.visibility = View.VISIBLE
                        playNextLevelButton.setText(R.string.play_finished)
                        playNextLevelButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.sensei, 0)
                        AppState.highScore.value = AppState.score.value

                        playNextLevelButton.setOnClickListener {
                            val intentPlayNextLevelButton = Intent(this, LevelsActivity::class.java);
                            startActivity(intentPlayNextLevelButton)
                            finish()
                        }

                    } else if(userInputField.text.toString() == answer.toString()) {

                        rightAnswer()
                        if(AppState.score.value!! > AppState.highScore.value!!){
                            AppState.highScore.value = AppState.score.value
                        }
                        currentScoreLevel += 1
                        playscreenScoreTextView.text = "Score: " + AppState.score.value?.toString()
                        showCurrentScoreLevel()

                        if(AppState.score.value == nextLevelIsAbove){
                            playNextLevelButton.visibility = View.VISIBLE
                            playNextLevelButton.setOnClickListener {

                                if( AppState.levelPlayedNow.value != 3){
                                    (AppState.levelPlayedNow.value) =
                                        (AppState.levelPlayedNow.value)?.plus(1)
                                    playGame();
                                }
                            }

                        } else {
                            newChallengeButton.visibility = View.VISIBLE
                            newChallengeButton.setOnClickListener {
                                playGame()
                            }
                        }

                    } else {
                        resultsTextView.visibility = View.VISIBLE
                        resultsTextView.text = "Sorry, better luck next time!"
                        answerImage.visibility = View.VISIBLE
                        answerImage.setImageResource(R.drawable.sad_avatar);
                        hideKeyboard()

                        restartLevelButton.visibility = View.VISIBLE
                        restartLevelButton.setOnClickListener {
                            val intentRestartButton = Intent(this, PlayActivity::class.java);
                            startActivity(intentRestartButton)
                            finish()
                        }
                    }

                    return@OnKeyListener true
                }
                false
            })
        }

        playGame()
    }
}

