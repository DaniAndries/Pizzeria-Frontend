package com.composePizzeria.data

data class PizzaDTO(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val size: Size,
    val ingredientes: List<IngredienteDTO>
)
