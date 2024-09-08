package com.one993.jetpackcomposecleanarchitecture.core.navigation_utils

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
}
