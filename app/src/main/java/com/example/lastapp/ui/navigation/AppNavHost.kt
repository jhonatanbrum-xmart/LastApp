package com.example.lastapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lastapp.ui.calendar.CalendarScreen
import com.example.lastapp.ui.home.HomeScreen
import com.example.lastapp.ui.notification.NotificationScreen

@Composable
fun AppNavHost(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController, startDestination = Home.route, modifier = modifier
    ) {
        composable(route = Home.route) {
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