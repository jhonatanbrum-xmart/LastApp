package com.example.lastapp.ui.notification

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen(
    onClickSeeFirst: () -> Unit,
    onClickSeeCalendar: () -> Unit
){
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
            onClick = onClickSeeFirst
        ) {
            Text("Continue to Main")
        }
    }
}