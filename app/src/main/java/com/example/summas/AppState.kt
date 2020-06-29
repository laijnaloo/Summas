package com.example.summas

object AppState {
    var score = 0
    var highScore = 10
    var levelPlayedNow = 0

    fun updateScore(newScore: Int){
        if(newScore > score){
            highScore = newScore;
        }
        score = newScore;
    }

    fun updateLevelPlayedNow(newLevel: Int){
        if(newLevel == 0 || newLevel == 1) {
            levelPlayedNow = newLevel
        }  else if(highScore >= 5  && newLevel == 2) {
            levelPlayedNow = newLevel
        }
        else if(highScore >= 10  && newLevel == 3) {
            levelPlayedNow = newLevel
        }
    }
}