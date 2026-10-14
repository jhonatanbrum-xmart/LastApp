package com.example.lastapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lastapp.ui.calendar.CalendarScreen
import com.example.lastapp.ui.home.HomeScreen
import com.example.lastapp.ui.notification.NotificationScreen
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

