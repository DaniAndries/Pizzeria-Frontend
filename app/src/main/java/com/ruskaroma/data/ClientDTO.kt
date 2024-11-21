package com.ruskaroma.data


/**
 * Data class representing a client in the system.
 * This class holds the information about the client such as their ID, DNI (identity number), name, address, phone, email,
 * password, and a list of orders associated with the client.
 *
 * @param id Unique identifier for the client. Default value is 0.
 * @param dni The client's DNI (identity number). This is a unique identifier for the client, often used in Latin American countries.
 * @param name The name of the client.
 * @param direction The address or direction where the client resides.
 * @param phone The phone number of the client.
 * @param mail The email address of the client.
 * @param password The password used by the client to authenticate into the system.
 * @param orders A list of orders placed by the client. The default value is an empty list.
 */
data class ClientDTO(
    /**
     * Unique identifier for the client.
     * Default is set to 0, to be updated when the client data is stored in the database or created.
     */
    var id: Int = 0,

    /**
     * The client's DNI (identity number).
     * Typically used for identification purposes.
     */
    var dni: String = "",

    /**
     * The full name of the client.
     * Represents the client's first and last name.
     */
    var name: String = "",

    /**
     * The client's address.
     * It represents the physical location where the client resides.
     */
    var direction: String = "",

    /**
     * The client's phone number.
     * Used for communication purposes.
     */
    var phone: String = "",

    /**
     * The client's email address.
     * Used for notifications, correspondence, or as a login credential.
     */
    var mail: String = "",

    /**
     * The client's password.
     * Used to authenticate the client when logging into the system.
     */
    var password: String = "",

    /**
     * A list of orders that the client has made.
     * Each order is represented by an `OrderDTO` object, and this list can be empty if the client has not placed any orders.
     */
    var orders: List<OrderDTO> = emptyList()
)
