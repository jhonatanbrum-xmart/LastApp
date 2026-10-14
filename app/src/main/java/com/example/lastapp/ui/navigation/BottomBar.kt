package com.example.lastapp.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.lastapp.ui.theme.MiColorGris

@Composable
fun TabRow(
    allScreens: List<AppNavigation>,
    onTabSelected: (AppNavigation) -> Unit,
    currentScreen: AppNavigation
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MiColorGris
    ) {
        allScreens.forEach { screen ->
            val isSelected = currentScreen == screen
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(screen) },
                label = {
                    Text(text = screen.route)
                },
                icon = {
                    val icon = if (isSelected) screen.selectedIcon else screen.notSelectedIcon
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = ""
                    )
                }
            )
        }

    }
}

