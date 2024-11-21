package com.ruskaroma.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composePizzeria.data.ClientDTO
import com.ruskaroma.R
import com.ruskaroma.ui.theme.RuskaRomaTheme


@Composable
fun RegisterScreen(viewModel: RegisterViewModel) {
    val buttonRegisterState: Boolean by viewModel.buttonRegisterState.observeAsState(false)
    val client: ClientDTO by viewModel.client.observeAsState(ClientDTO())
    val errorMessage: ErrorMessage by viewModel.errorMessage.observeAsState(ErrorMessage())
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            RegisterForm(buttonRegisterState, viewModel, client, errorMessage)
        }
    }
}

@Composable
fun RegisterForm(
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
            onValueChange = { viewModel.onClientChange(client.copy(direction = it)) },
            text = "Direction",
            value = client.direction
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
    }
}

/**
 *     val Name:String,
 *     val DNI:String,
 *     val Direction:String,
 *     val Telephone:String,
 *     val Email:String,
 *     val Password:String,
 */
@Composable
fun FormFields(
    error: String = "",
    onValueChange: (String) -> Unit,
    text: String,
    keyboard: KeyboardType = KeyboardType.Text,
    value: String
) {
    TextField(
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
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

@Composable
fun PasswordField(
    error: String = "",
    onValueChange: (String) -> Unit,
    text: String,
    value: String
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    TextField(
        onValueChange = { onValueChange(it) },
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
            val image = if (passwordVisible)
                Icons.Filled.Visibility
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
fun RegisterPreview() {
    RuskaRomaTheme {
        RegisterScreen(RegisterViewModel())
    }
}
