package com.ruskaroma.ui.registro

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.composePizzeria.data.ClienteDTO

class RegisterViewModel : ViewModel() {
    val buttonRegisterState = MutableLiveData(false)
    var client = MutableLiveData<ClienteDTO>()
    val errorMessage = MutableLiveData<ErrorMessage>()

    fun onClientChange(newCliente: ClienteDTO) {
        client.value = newCliente
        errorMessage.value = ErrorMessage(
            name = (if (!newCliente.nombre.matches(regex = "^[A-Za-zÀ-ÿ'\\- ]{2,50}$".toRegex())) "Name format is not valid" else " "),
            email = (if (!newCliente.email.matches(regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) "Email format is not valid" else " "),
            passwrd = (if (!newCliente.password.matches(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}\$".toRegex())) "Password format is not valid" else " ")
        )
        buttonRegisterState.value =
            (newCliente.dni.isNotBlank() && newCliente.nombre.isNotBlank() && newCliente.direccion.isNotBlank() && newCliente.telefono.isNotBlank() && newCliente.email.isNotBlank() && newCliente.password.isNotBlank())
    }
}
