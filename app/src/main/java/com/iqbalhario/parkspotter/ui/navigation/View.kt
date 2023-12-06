package com.iqbalhario.parkspotter.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Cart : Screen("map")
    object Profile : Screen("setting")
}