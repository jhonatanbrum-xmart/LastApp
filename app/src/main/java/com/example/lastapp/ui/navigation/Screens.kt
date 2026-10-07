package com.example.lastapp.ui.navigation

import com.example.lastapp.R

interface AppNavigation {
    val icon: Int
    val route: String
}

object First : AppNavigation {
    override val icon = R.drawable.home
    override val route = "Home"
}

object Notification : AppNavigation {
    override val icon = R.drawable.notifications
    override val route = "Notifications"
}

object Calendar : AppNavigation {
    override val icon = R.drawable.calendar_month
    override val route = "Calendar"
}

val tabRowScreens = listOf(First, Calendar, Notification)