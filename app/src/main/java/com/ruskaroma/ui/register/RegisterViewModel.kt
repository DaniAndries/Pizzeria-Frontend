package com.ruskaroma.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaroma.data.model.ClientDTO
import com.ruskaroma.data.repositoies.ClientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel class that handles the state and business logic for the user registration process.
 * It stores the client data, error messages, and manages the register button state.
 */
class RegisterViewModel (private val clientRepository: ClientRepository): ViewModel() {
    /**
     * LiveData that holds the state of the register button.
     * It determines whether the register button is enabled or disabled based on the input form.
     */
    val buttonRegisterState = MutableLiveData(false)

    /**
     * LiveData that holds the client data, including name, DNI, direction, phone number, email, and password.
     * It is used to bind the client information to the registration form UI.
     */
    var client = MutableLiveData<ClientDTO>()

    /**
     * LiveData that holds any error messages related to the client's form input.
     * It is used to display validation messages below the respective form fields.
     */
    val errorMessage = MutableLiveData<ErrorMessage>()

    /**
     * Function that updates the client data and validates the inputs.
     * It updates the error messages for invalid inputs and enables/disables the register button.
     *
     * @param newCliente The updated client data containing name, DNI, direction, phone, email, and password.
     */
    fun onClientChange(newCliente: ClientDTO) {
        client.value = newCliente

        // Validate inputs and set error messages
        errorMessage.value = ErrorMessage(
            name = (if (!newCliente.name.matches(regex = "^[A-Za-zÀ-ÿ'\\- ]{2,50}$".toRegex())) "Name format is not valid" else " "),
            email = (if (!newCliente.mail.matches(regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) "Email format is not valid" else " "),
            passwrd = (if (!newCliente.password.matches(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!.]).{8,}\$".toRegex())) "Password format is not valid" else " ")
        )

        // Enable register button if all fields are filled in correctly
        buttonRegisterState.value =
            (newCliente.dni.isNotBlank() && newCliente.name.isNotBlank() && newCliente.address.isNotBlank() && newCliente.phone.isNotBlank() && newCliente.mail.isNotBlank() && newCliente.password.isNotBlank())
    }

    /**
     * Function called when the user clicks the register button.
     * Currently logs the client data to the console.
     */
    fun onRegisterClick() {
        val actualClient = client.value
        if (actualClient != null) {
            viewModelScope.launch {
                val result =
                    clientRepository.registerClient(actualClient)
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> {
                            client.value = result.getOrThrow()
                        }
                        false -> {
                            Log.d("LOG", "Error:$result")
                        }
                    }
                }
            }
        }
    }
}
