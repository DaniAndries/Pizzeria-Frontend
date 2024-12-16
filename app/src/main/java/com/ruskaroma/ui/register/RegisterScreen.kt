package com.ruskaroma.ui.register

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ruskaroma.R
import com.ruskaroma.data.model.ClientDTO
import com.ruskaroma.navigator.AppNavigation
import com.ruskaroma.navigator.Screen
import com.ruskaroma.ui.theme.RuskaRomaTheme

/**
 * Composable function to display the registration screen. It observes the state of the register button
 * and error messages, and passes them along with the client data to the `RegisterForm` composable.
 *
 * @param viewModel The `RegisterViewModel` which holds the client data, error messages, and button state.
 */
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {
    val buttonRegisterState: Boolean by viewModel.buttonRegisterState.observeAsState(false)
    val client: ClientDTO by viewModel.client.observeAsState(ClientDTO())
    val errorMessage: ErrorMessage by viewModel.errorMessage.observeAsState(ErrorMessage())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            RegisterForm(navController, buttonRegisterState, viewModel, client, errorMessage)
        }
    }
}

/**
 * Composable function that renders the form for user registration, including fields for name, DNI,
 * direction, phone number, email, and password.
 *
 * @param buttonRegisterState Boolean that determines if the register button is enabled.
 * @param viewModel The `RegisterViewModel` that handles the logic behind client data changes.
 * @param client The client data containing the information entered by the user.
 * @param errorMessage The error messages that should be displayed under specific fields.
 */
@Composable
fun RegisterForm(
    navController: NavController,
    buttonRegisterState: Boolean,
    viewModel: RegisterViewModel,
    client: ClientDTO,
    errorMessage: ErrorMessage
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ruskaromalogo),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .padding(top = 16.dp, bottom = 12.dp, end = 20.dp, start = 20.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = true)
        )
        FormFields(
            error = errorMessage.name,
            onValueChange = { viewModel.onClientChange(client.copy(name = it)) },
            "Name",
            value = client.name,
        )
        FormFields(
            onValueChange = { viewModel.onClientChange(client.copy(dni = it)) },
            text = "DNI",
            value = client.dni
        )
        FormFields(
            onValueChange = { viewModel.onClientChange(client.copy(address = it)) },
            text = "Direction",
            value = client.address
        )
        FormFields(
            onValueChange = { viewModel.onClientChange(client.copy(phone = it)) },
            text = "Telephone",
            keyboard = KeyboardType.Phone,
            value = client.phone
        )
        FormFields(
            error = errorMessage.email,
            onValueChange = { viewModel.onClientChange(client.copy(mail = it)) },
            text = "Email",
            keyboard = KeyboardType.Email,
            value = client.mail
        )
        PasswordField(
            error = errorMessage.passwrd,
            onValueChange = { viewModel.onClientChange(client.copy(password = it)) },
            text = "Password",
            value = client.password
        )


        Button(
            enabled = buttonRegisterState,
            onClick = { viewModel.onRegisterClick() },
            modifier = Modifier
                .width(300.dp)
                .size(85.dp)
                .padding(16.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) { Text("Register") }
        Row() {
            Text("Already have an account? ")
            Text(color = MaterialTheme.colorScheme.primary,
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
                        navController.navigate(Screen.Login.route)
                    },
                text = "Login Now"
            )
        }
    }
}

// Name, DNI, Direction, Phone, Email, and Password input fields
/**
 * A composable function that renders a text input field.
 *
 * @param error Error message to display below the text field, if any.
 * @param onValueChange A lambda function to handle changes in the text field.
 * @param text The placeholder text to be displayed in the text field.
 * @param keyboard The keyboard type to be used (default is Text).
 * @param value The current value of the text field.
 */
@Composable
fun FormFields(
    error: String = "",
    onValueChange: (String) -> Unit,
    text: String,
    keyboard: KeyboardType = KeyboardType.Text,
    value: String
) {
    TextField(keyboardOptions = KeyboardOptions(keyboardType = keyboard),
        placeholder = { Text(text) },
        modifier = Modifier
            .width(300.dp)
            .padding(16.dp)
            .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        value = value,
        onValueChange = { onValueChange(it) })

    if (error.isNotBlank() && value.isNotBlank()) {
        Text(color = MaterialTheme.colorScheme.error, text = error)
    }
}

/**
 * A composable function that renders a password input field with the option to show or hide the password.
 *
 * @param error Error message to display below the password field, if any.
 * @param onValueChange A lambda function to handle changes in the password field.
 * @param text The placeholder text to be displayed in the password field.
 * @param value The current value of the password field.
 */
@Composable
fun PasswordField(
    error: String = "", onValueChange: (String) -> Unit, text: String, value: String
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(onValueChange = { onValueChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        placeholder = { Text(text) },
        modifier = Modifier
            .width(300.dp)
            .padding(16.dp)
            .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        value = value,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        })
    if (error.isNotBlank() && value.isNotBlank()) {
        Text(color = MaterialTheme.colorScheme.error, text = error)
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
