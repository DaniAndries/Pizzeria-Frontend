package com.composePizzeria.data

data class ClienteDTO(
    var id: Int = 0,
    var dni: String = "",
    var nombre: String = "",
    var direccion: String = "",
    var telefono: String = "",
    var email: String = "",
    var password: String = "",
    var pedidos: List<PedidoDTO> = emptyList()
)
