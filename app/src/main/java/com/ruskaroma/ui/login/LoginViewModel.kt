package com.ruskaroma.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ruskaroma.data.LoginDTO
import com.ruskaroma.ui.register.ErrorMessage

class LoginViewModel {
    val buttonRegisterState = MutableLiveData(false)
    var login = MutableLiveData<LoginDTO>()
    val errorMessage = MutableLiveData<ErrorMessage>()


    fun onClientChange(newLogin: LoginDTO) {
        login.value = newLogin
        errorMessage.value = ErrorMessage(
            email = (if (!newLogin.email.matches(regex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) "Email format is not valid" else " "),
            passwrd = (if (!newLogin.password.matches(regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#\$%^&+=!.]).{8,}\$".toRegex())) "Password format is not valid" else " ")
        )
        buttonRegisterState.value =
            (newLogin.email.isNotBlank() && newLogin.password.isNotBlank())
    }

    fun onLoginClick(){
        Log.d("Login", "${login.value}")
    }
}