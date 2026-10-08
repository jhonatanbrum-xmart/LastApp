package com.example.lastapp.ui.navigation

import com.example.lastapp.R

interface AppNavigation {
    val notSelectedIcon: Int
    val selectedIcon: Int
    val route: String
}

object First : AppNavigation {
    override val notSelectedIcon = R.drawable.home
    override val selectedIcon = R.drawable.home_black
    override val route = "Home"
}

object Notification : AppNavigation {
    override val notSelectedIcon = R.drawable.notifications
    override val selectedIcon = R.drawable.notifications_black
    override val route = "Notifications"
}

object Calendar : AppNavigation {
    override val notSelectedIcon = R.drawable.calendar_month
    override val selectedIcon = R.drawable.calendar_month_black
    override val route = "Calendar"
}

val tabRowScreens = listOf(First, Calendar, Notification)