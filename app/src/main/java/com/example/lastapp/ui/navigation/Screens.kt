package com.example.lastapp.ui.navigation

import com.example.lastapp.R

interface AppNavegation {
    val icon: Int
    val route: String
}

object First : AppNavegation {
    override val icon = R.drawable.icons8_casa
    override val route = "first"
}

object Notification : AppNavegation {
    override val icon = R.drawable.icons8_campana_50
    override val route = "notification"
}

object Calendar : AppNavegation {
    override val icon = R.drawable.icons8_calendario_64
    override val route = "calendar"
}

val tabRowScreens = listOf(First, Notification, Calendar)