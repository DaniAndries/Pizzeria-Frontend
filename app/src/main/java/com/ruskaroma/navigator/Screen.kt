package com.ruskaroma.navigator

sealed class Screen(val route: String) {
    data object Register : Screen("register")
    data object Login : Screen("login")
    data object Home : Screen("home")

    companion object {
        val screens = listOf(Screen.Home)
    }
}
