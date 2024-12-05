package com.ruskaroma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ruskaroma.navigator.AppNavigation

import com.ruskaroma.ui.register.RegisterScreen
import com.ruskaroma.ui.register.RegisterViewModel
import com.ruskaroma.ui.theme.RuskaRomaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuskaRomaTheme {
                val navController = rememberNavController() 
                AppNavigation(navController = navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RuskaRomaTheme {
        val navController = rememberNavController()
        AppNavigation(navController = navController)
    }
}