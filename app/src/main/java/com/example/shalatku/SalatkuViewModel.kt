package com.example.shalatku

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SalatkuViewModel : ViewModel() {
    // --- Tasbih ---
    var roundSize by mutableIntStateOf(33)
        private set
    var tasbihCount by mutableIntStateOf(0)
        private set
    var completeRounds by mutableIntStateOf(0)
        private set

    fun setRound(size: Int){
        roundSize = size
        tasbihCount = 0
    }
    fun incrementTasbih() {
        tasbihCount++
        if (tasbihCount == roundSize){
            completeRounds++
            tasbihCount = 0
        }
    }

    fun resetTasbih() {
        tasbihCount = 0
        completeRounds = 0
    }

    // --- Tracker ---
    private val _donePrayers = mutableStateListOf<String>()
    val donePrayers: List<String> get() = _donePrayers

    fun togglePrayer(name: String) {
        if (name in _donePrayers) _donePrayers.remove(name) else _donePrayers.add(name)
    }

    fun clearPrayers() {
        _donePrayers.clear()
    }

    // --- Home ---
    var selectedDay by mutableIntStateOf(0)
        private set

    fun selectDay(index: Int) {
        selectedDay = index
    }
}