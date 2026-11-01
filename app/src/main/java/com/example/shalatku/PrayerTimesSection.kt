package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PrayerTimesSection(
    state: PrayerTimesUiState,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is PrayerTimesUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
            is PrayerTimesUiState.Success -> {
                Column {
                    state.times.forEach { prayer ->
                        PrayerCard(prayer)
                    }
                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = onRefresh,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Refresh")
                    }
                }
            }
            is PrayerTimesUiState.Error -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Button(onClick = onRefresh) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionLoadingPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Loading,
        onRefresh = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionSuccessPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Success(samplePrayers),
        onRefresh = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PrayerTimesSectionErrorPreview() {
    PrayerTimesSection(
        state = PrayerTimesUiState.Error("Gagal memuat data."),
        onRefresh = {}
    )
}