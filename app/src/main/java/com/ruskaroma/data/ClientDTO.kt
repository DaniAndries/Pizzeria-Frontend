package com.composePizzeria.data

data class ClientDTO(
    var id: Int = 0,
    var dni: String = "",
    var name: String = "",
    var direction: String = "",
    var phone: String = "",
    var mail: String = "",
    var password: String = "",
    var orders: List<OrderDTO> = emptyList()
)
