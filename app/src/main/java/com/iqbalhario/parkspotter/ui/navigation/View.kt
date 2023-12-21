package com.iqbalhario.parkspotter.ui.navigation

sealed class Screen (val route: String) {
    object Home : Screen("home")
    object Map : Screen("map")
    object Profile : Screen("setting")
    object DetailParkir: Screen("detail_parkir/{parkirId}") {
        fun createRoute(parkirId: Int) = "detail_parkir/$parkirId"
    }
}