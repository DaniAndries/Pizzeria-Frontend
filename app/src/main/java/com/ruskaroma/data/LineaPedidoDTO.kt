package com.composePizzeria.data

data class LineaPedidoDTO(
    val id: Int,
    val cantidad: Int,
    val pizzaProduct: PizzaDTO?,
    val productPaste: PastaDTO?,
    val productDrink: BebidaDTO?
)
