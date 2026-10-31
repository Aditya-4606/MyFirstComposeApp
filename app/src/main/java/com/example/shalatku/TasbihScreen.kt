package com.example.shalatku

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TasbihScreen(
    count: Int,
    rounds: Int,
    onSetRoundSize: (Int) -> Unit,
    onIncrement:() -> Unit,
    onReset:() -> Unit,
    modifier: Modifier = Modifier) {

    Column(
        modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tasbih", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))

        Text("$count", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(24.dp))
        Text("$rounds", style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(24.dp))
        Button(onClick = onIncrement) { Text("+1") }
        Spacer(Modifier.height(24.dp))

        OutlinedButton(onClick = onReset) { Text("Reset") }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { onSetRoundSize(33) }) { Text("33") }
            Button(onClick = { onSetRoundSize(99) }) { Text("99") }
            Button(onClick = { onSetRoundSize(100) }) { Text("100") }
        }
    }
}

@Preview
@Composable
fun TasbihScreenPreview() {
    TasbihScreen(
        count = 12,
        rounds = 2,
        onIncrement = {},
        onSetRoundSize = {},
        onReset = {}
    )
}
