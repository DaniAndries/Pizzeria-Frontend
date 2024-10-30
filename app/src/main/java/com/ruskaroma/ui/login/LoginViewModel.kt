package com.ruskaroma.ui.login

import androidx.lifecycle.MutableLiveData

class LoginViewModel {
    val text = MutableLiveData("")

    fun onTextChange(newText: String) {
        text.value = newText
    }
}