package com.ruskaroma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ruskaroma.ui.home.HomeScreen
import com.ruskaroma.ui.home.HomeViewModel
import com.ruskaroma.ui.register.RegisterScreen
import com.ruskaroma.ui.register.RegisterViewModel
import com.ruskaroma.ui.theme.RuskaRomaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RuskaRomaTheme {
                HomeScreen(HomeViewModel())
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RuskaRomaTheme {
        RegisterScreen(RegisterViewModel())
    }
}