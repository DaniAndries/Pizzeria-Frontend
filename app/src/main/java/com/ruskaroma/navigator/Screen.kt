package com.ruskaroma.navigator

sealed class Screen(val route: String) {
    data object Register : Screen("register")
    data object Login : Screen("login")
    data object Home : Screen("home")

    val screens = listOf(Screen.Register)
}
