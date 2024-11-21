package com.ruskaroma.ui.register

/**
 * Data class that holds error messages related to the registration or login form.
 * It contains fields for errors related to name, email, and password.
 *
 * @property name Error message related to the name field (default is an empty string).
 * @property email Error message related to the email field (default is an empty string).
 * @property passwrd Error message related to the password field (default is an empty string).
 */
data class ErrorMessage(
    var name: String="",
    var email: String="",
    var passwrd: String=""
)
