package com.ruskaroma.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ruskaroma.R
import com.ruskaroma.ui.registro.FormFields
import com.ruskaroma.ui.registro.PasswordField
import com.ruskaroma.ui.theme.RuskaRomaTheme

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val texto: String by viewModel.text.observeAsState("")
    LoginForm(texto) { viewModel.onTextChange(it) }
}

@Composable
fun LoginForm(texto: String, onTextChange: (String) -> Unit) {
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


        Text(text = texto)

    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .width(300.dp)
                .padding(16.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
            containerColor = MaterialTheme.colorScheme.primary,

            ) { Text("Login") }
        FloatingActionButton(
            onClick = {},
            modifier = Modifier
                .width(300.dp)
                .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                .shadow(20.dp, RoundedCornerShape(10.dp), clip = false),
            containerColor = MaterialTheme.colorScheme.primary,

            ) { Text("Register") }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    RuskaRomaTheme {
        LoginScreen(LoginViewModel())
    }
}