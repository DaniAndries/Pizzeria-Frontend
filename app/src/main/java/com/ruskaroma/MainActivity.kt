package com.ruskaroma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ruskaroma.ui.registro.RegisterScreen
import com.ruskaroma.ui.registro.RegisterViewModel
import com.ruskaroma.ui.theme.RuskaRomaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RuskaRomaTheme {
                RegisterScreen(RegisterViewModel())
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