package com.example.lastapp.ui.navigation

import com.example.lastapp.R

interface AppNavigation {

    val route: String
}

interface bottomBarNavigation : AppNavigation {
    val notSelectedIcon: Int
    val selectedIcon: Int
    val label: Int
}

object Home : bottomBarNavigation {
    override val notSelectedIcon = R.drawable.home
    override val selectedIcon = R.drawable.home_black
    override val route = "Home"
    override val label = R.string.home_textIcon
}

object Notification : bottomBarNavigation {
    override val notSelectedIcon = R.drawable.notifications
    override val selectedIcon = R.drawable.notifications_black
    override val route = "Notifications"
    override val label = R.string.notification_textIcon
}

object Calendar : bottomBarNavigation {
    override val notSelectedIcon = R.drawable.calendar_month
    override val selectedIcon = R.drawable.calendar_month_black
    override val route = "Calendar"
    override val label = R.string.calendar_textIcon
}

val tabRowScreens = listOf(Home, Calendar, Notification)