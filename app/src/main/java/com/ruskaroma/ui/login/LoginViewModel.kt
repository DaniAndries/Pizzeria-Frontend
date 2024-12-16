package com.ruskaroma.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaroma.data.model.LoginDTO
import com.ruskaroma.data.repositoies.ClientRepository
import com.ruskaroma.ui.register.ErrorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel class responsible for managing login data and validation.
 * It holds the state for the login form, including email, password, error messages,
 * and the state of the register button.
 */
class LoginViewModel (private val clientRepository: ClientRepository): ViewModel() {
    val loading = MutableLiveData(false)
    val buttonRegisterState = MutableLiveData(false)
    var login = MutableLiveData<LoginDTO>()
    val errorMessage = MutableLiveData<ErrorMessage>()

    /**
     * Updates the login data and validates the input fields (email and password).
     *
     * @param newLogin A new LoginDTO instance containing updated email and password.
     * The email and password are validated using regex patterns, and error messages
     * are generated accordingly. The register button state is also updated based on
     * whether the fields are valid and not empty.
     */
    fun onClientChange(newLogin: LoginDTO) {
        login.value = newLogin
        errorMessage.value = ErrorMessage(
            email = (if (!newLogin.mail.matches(regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) "Email format is not valid" else " "),
            passwrd = (if (!newLogin.password.matches(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!.]).{8,}\$".toRegex())) "Password format is not valid" else " ")
        )
        buttonRegisterState.value =
            (newLogin.mail.isNotBlank() && newLogin.password.isNotBlank())
    }

    /**
     * Logs the current login data when the login button is clicked.
     * This method is typically called to handle the login logic (authentication).
     * For now, it just logs the current login data to the debug log.
     */
    fun onLoginClick(callback: (Boolean) -> Unit){
        loading.value = true
        val actualClient = login.value
        if (actualClient != null) {
            viewModelScope.launch {
                val result = clientRepository.loginClient(actualClient)
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> {
                            login.value = result.getOrThrow()
                            callback(true)
                            loading.value=false
                        }
                        false -> {
                            Log.d("Login", "Error:$result")
                            callback(false)
                            loading.value=false
                        }
                    }
                }
            }
        }
        Log.d("Login", "$actualClient")
        Log.d("Login", "${login.value}")
    }
}