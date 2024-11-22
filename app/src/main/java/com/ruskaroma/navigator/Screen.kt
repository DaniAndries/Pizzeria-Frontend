package com.ruskaroma.navigator

sealed class Screen(val route: String) {
    object Register : Screen("register")
    object Login : Screen("login")
    object Home : Screen("home")
}
