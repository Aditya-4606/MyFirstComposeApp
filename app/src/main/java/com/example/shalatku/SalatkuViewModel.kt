package com.example.shalatku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SalatkuViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SalatkuUiState())
    val uiState: StateFlow<SalatkuUiState> = _uiState.asStateFlow()

    private var callCounter = 0
    fun loadPrayerTimes() {
        if (_uiState.value.prayerTimes is PrayerTimesUiState.Loading) return

        _uiState.update { it.copy(prayerTimes = PrayerTimesUiState.Loading) }

        viewModelScope.launch {
            delay(1500)
            callCounter++

            _uiState.update { state ->
                if (callCounter % 2 == 0) {
                    state.copy(prayerTimes = PrayerTimesUiState.Error("Gagal mengambil data. Coba lagi."))
                } else {
                    state.copy(prayerTimes = PrayerTimesUiState.Success(samplePrayers))
                }
            }
        }
    }
    fun togglePrayer(name: String) {
        _uiState.update { state ->
            val done = if (name in state.donePrayers) {
                state.donePrayers - name
            } else {
                state.donePrayers + name
            }
            state.copy(donePrayers = done)
        }
    }

    fun clearPrayers() {
        _uiState.update { it.copy(donePrayers = emptyList()) }
    }

    fun selectDay(index: Int) {
        _uiState.update { it.copy(selectedDay = index) }
    }

    fun incrementTasbih() {
        _uiState.update { state ->
            val next = state.tasbihCount + 1
            if (next == state.roundSize) {
                state.copy(tasbihCount = 0, completedRounds = state.completedRounds + 1)
            } else {
                state.copy(tasbihCount = next)
            }
        }
    }

    fun resetTasbih() {
        _uiState.update { it.copy(tasbihCount = 0, completedRounds = 0) }
    }

    fun setRound(size: Int) {
        // HW12 rule: a new target resets the current count but keeps the completed rounds.
        _uiState.update { it.copy(roundSize = size, tasbihCount = 0) }
    }
}