package com.composePizzeria.data

import java.util.Date

data class OrderDTO(
    val id: Int,
    var orderDate: Date,
    val status: ORDER_STATUS,
    val totalPrice: Double,
    var orderLineDTO: MutableList<OrderLineDTO>,
)
