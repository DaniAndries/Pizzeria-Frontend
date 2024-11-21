package com.ruskaroma.ui.register

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.composePizzeria.data.ClientDTO

class RegisterViewModel : ViewModel() {
    val buttonRegisterState = MutableLiveData(false)
    var client = MutableLiveData<ClientDTO>()
    val errorMessage = MutableLiveData<ErrorMessage>()

    fun onClientChange(newCliente: ClientDTO) {
        client.value = newCliente
        errorMessage.value = ErrorMessage(
            name = (if (!newCliente.name.matches(regex = "^[A-Za-zÀ-ÿ'\\- ]{2,50}$".toRegex())) "Name format is not valid" else " "),
            email = (if (!newCliente.mail.matches(regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) "Email format is not valid" else " "),
            passwrd = (if (!newCliente.password.matches(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!.]).{8,}\$".toRegex())) "Password format is not valid" else " ")
        )
        buttonRegisterState.value =
            (newCliente.dni.isNotBlank() && newCliente.name.isNotBlank() && newCliente.direction.isNotBlank() && newCliente.phone.isNotBlank() && newCliente.mail.isNotBlank() && newCliente.password.isNotBlank())
    }

    fun onRegisterClick() {
        Log.d("Login", "${client.value}")
    }
}
