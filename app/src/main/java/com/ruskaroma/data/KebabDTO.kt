package com.composePizzeria.data

data class KebabDTO(
    val id: Int,
    val nombre: String,
    var precio: Double,
    val ingredientes: List<IngredienteDTO>,
    val size: Size,
    val carne: TIPO_CARNE
)
