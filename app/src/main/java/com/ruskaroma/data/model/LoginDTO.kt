package com.ruskaroma.data.model

/**
 * Data class representing the login credentials for a user.
 * This class contains the user's email and password used for authentication in the system.
 *
 * @param email The email address of the user. It is used to identify the user during login.
 * @param password The password associated with the user's account, used for authentication.
 */
data class LoginDTO(
    /**
     * The email address of the user.
     * This value is used to uniquely identify the user and is typically required for login.
     */
    val email: String = "",

    /**
     * The password associated with the user's account.
     * This is a secure value used to authenticate the user during the login process.
     */
    val password: String = ""
)
