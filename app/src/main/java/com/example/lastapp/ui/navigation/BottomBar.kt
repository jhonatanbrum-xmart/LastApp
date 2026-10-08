package com.example.lastapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Spacer(modifier = Modifier.weight(0.35f))
                AppTab(
                    text = screen.route,
                    notSelectedIcon = screen.notSelectedIcon,
                    selectedIcon = screen.selectedIcon,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen,
                )
                Spacer(modifier = Modifier.weight(0.13f))
            }

        }
    }

@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = First.route, modifier = modifier
    ) {
        composable(route = First.route) {
            HomeScreen()
        }
        composable(route = Calendar.route) {
            CalendarScreen()
        }
        composable(route = Notification.route) {
            NotificationScreen()
        }

    }
}

@Composable
fun AppTab(
    text: String,
    @DrawableRes notSelectedIcon: Int,
    @DrawableRes selectedIcon: Int,
    onSelected: () -> Unit,
    selected: Boolean,
) {

    val icon = if(selected) selectedIcon else notSelectedIcon
    Column(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .animateContentSize()
                .selectable(
                    selected = selected,
                    onClick = onSelected,
                    role = Role.Tab,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        bounded = true,
                        radius = 20.dp,
                        color = Color.Blue
                    )
                )
                .clearAndSetSemantics { contentDescription = text },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(painter = painterResource(id = icon), contentDescription = "")
            Spacer(modifier = Modifier.size(8.dp))
            Text(text)
        }
    }


private val TabHeight = 80.dp