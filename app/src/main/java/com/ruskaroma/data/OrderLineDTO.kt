package com.ruskaroma.data

import com.ruskaroma.data.ProductDTO

/**
 * Data class representing a line in an order.
 * This class stores information about a specific product in the order,
 * including the product type, quantity, and a unique identifier for the line.
 *
 * @property id The unique identifier for the order line.
 * @property amount The quantity of the product ordered.
 * @property productType The type of product being ordered, represented by a [ProductDTO] object.
 */
data class OrderLineDTO(
    val id: Int,
    val amount: Int,
    val productType: ProductDTO
)
