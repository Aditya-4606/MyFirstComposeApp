package com.example.shalatku

data class SalatkuUiState(
    val prayerTimes: PrayerTimesUiState = PrayerTimesUiState.Success(samplePrayers),
    val donePrayers: List<String> = emptyList(),
    val selectedDay: Int = 0,
    val tasbihCount: Int = 0,
    val completedRounds: Int = 0,
    val roundSize: Int = 33,
) {
    val doneCount: Int
        get() = donePrayers.size
}