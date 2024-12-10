package com.ruskaroma.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ruskaroma.R
import com.ruskaroma.data.model.LoginDTO
import com.ruskaroma.navigator.AppNavigation
import com.ruskaroma.navigator.Screen
import com.ruskaroma.ui.register.ErrorMessage
import com.ruskaroma.ui.register.FormFields
import com.ruskaroma.ui.register.PasswordField
import com.ruskaroma.ui.theme.RuskaRomaTheme

/**
 * Displays the login screen, which includes the login form and button states.
 *
 * @param viewModel The ViewModel to manage the state and handle user actions.
 */
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    val buttonRegisterState: Boolean by viewModel.buttonRegisterState.observeAsState(false)
    val login: LoginDTO by viewModel.login.observeAsState(LoginDTO())
    val errorMessage: ErrorMessage by viewModel.errorMessage.observeAsState(ErrorMessage())
    LoginForm(navController, buttonRegisterState, viewModel, login, errorMessage)
}

/**
 * Displays the login form UI with fields for email and password, and buttons for login and registration.
 *
 * @param buttonState State of the register button (enabled or disabled).
 * @param viewModel The ViewModel to manage the form logic and user actions.
 * @param login The current login data (email and password).
 * @param errorMessage The error messages for email and password fields.
 */
@Composable
fun LoginForm(
    navController: NavController,
    buttonState: Boolean,
    viewModel: LoginViewModel,
    login: LoginDTO,
    errorMessage: ErrorMessage
) {
    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ruskaromalogo),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 16.dp, bottom = 12.dp, end = 20.dp, start = 20.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = true)
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        FormFields(
            error = errorMessage.email,
            onValueChange = { viewModel.onClientChange(login.copy(email = it)) },
            text = "Email",
            keyboard = KeyboardType.Email,
            value = login.email
        )
        PasswordField(
            error = errorMessage.passwrd,
            onValueChange = { viewModel.onClientChange(login.copy(password = it)) },
            text = "Password",
            value = login.password
        )
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            enabled = buttonState,
            onClick = { viewModel.onLoginClick(navController) },
            modifier = Modifier
                .width(300.dp)
                .size(90.dp)
                .padding(16.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) { Text("Login") }
        Row() {
            Text("Don't have an account? ")
            Text(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .drawBehind {
                        val strokeWidthPx = 1.dp.toPx()
                        val verticalOffset = size.height - 2.sp.toPx()
                        drawLine(
                            color = Color(0xFF6E1B3A),
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, verticalOffset),
                            end = Offset(size.width, verticalOffset)
                        )
                    }
                    .clickable {
                        navController.navigate(Screen.Register.route)
                    },
                text = "Sing Up Now"
            )
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