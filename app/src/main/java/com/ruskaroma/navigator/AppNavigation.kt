package com.ruskaroma.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ruskaroma.data.network.RetrofitInstance
import com.ruskaroma.data.repositoies.ClientRepository
import com.ruskaroma.data.repositoies.ProductRepository
import com.ruskaroma.ui.home.HomeScreen
import com.ruskaroma.ui.home.HomeViewModel
import com.ruskaroma.ui.login.LoginScreen
import com.ruskaroma.ui.login.LoginViewModel
import com.ruskaroma.ui.register.RegisterScreen
import com.ruskaroma.ui.register.RegisterViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val clientRepository = ClientRepository(RetrofitInstance.clientApi)
    val productRepository = ProductRepository(RetrofitInstance.productApi)


    NavHost(
        navController = navController, startDestination = Screen.Login.route // Pantalla inicial
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                viewModel = LoginViewModel(), navController = navController
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                viewModel = RegisterViewModel(), navController = navController
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = HomeViewModel(), navController = navController
            )
        }
    }
}