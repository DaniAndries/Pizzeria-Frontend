package com.ruskaroma.data

import java.util.Date

/**
 * Data class representing an order in the system.
 * This class is used to store the details of a specific order, including its status,
 * order date, total price, and the associated order lines.
 *
 * @property id The unique identifier for the order.
 * @property orderDate The date and time when the order was placed.
 * @property status The current status of the order (e.g., pending, delivered).
 * @property totalPrice The total price of the order.
 * @property orderLineDTO A list of items in the order, represented as order lines.
 */
data class OrderDTO(
    val id: Int,
    var orderDate: Date,
    val status: ORDER_STATUS,
    var totalPrice: Double,
    var orderLineDTO: MutableList<OrderLineDTO>
)
