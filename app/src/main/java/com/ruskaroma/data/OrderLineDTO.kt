package com.composePizzeria.data

import com.ruskaroma.data.ProductDTO

data class OrderLineDTO(
    val id: Int,
    val amount: Int,
    val productType: ProductDTO,
)
