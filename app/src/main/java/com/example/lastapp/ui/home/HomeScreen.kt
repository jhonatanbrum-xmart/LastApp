package com.example.lastapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onClickSeeCalendar: () -> Unit = {},
    onClickSeeNotifies: () -> Unit = {},
    modifier: Modifier = Modifier.Companion

) {
    val materiaEscrito = "Matematicas CTS"
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column() {
                Text("Notify 1")
                Text("Notify 2")
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {}) { Text("Random Button") }
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Text("Hello! You have writing of: $materiaEscrito today!")
        Spacer(modifier = Modifier.weight(1f))
        Row() {
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = onClickSeeCalendar
            ) {
                Text("Continue to Calendar")
            }
            Button(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                onClick = onClickSeeNotifies
            ) {
                Text("Continue to Notifies")
            }
        }
    }

}