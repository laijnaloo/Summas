package com.example.summas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

object AppState: ViewModel() {
    val score = MutableLiveData<Int>()
    val highScore = MutableLiveData<Int>()
    val levelPlayedNow = MutableLiveData<Int>()

    init {
        score.value = 0
        highScore.value = 0
        levelPlayedNow.value = 0
    }
}