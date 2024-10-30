package com.composePizzeria.data

import com.composePizzeria.data.ClienteDTO
import com.composePizzeria.data.EstadoPedido
import com.composePizzeria.data.LineaPedidoDTO
import java.util.Date

data class PedidoDTO(
    val id: Int,
    val fecha: Date,
    val estado: EstadoPedido,
    val precioTotal: Double,
    var lineaPedidoDTO: List<LineaPedidoDTO>,
    val clienteActual: ClienteDTO
)
