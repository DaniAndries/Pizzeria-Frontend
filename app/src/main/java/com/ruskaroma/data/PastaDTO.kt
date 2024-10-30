package com.composePizzeria.data

import com.composePizzeria.data.IngredienteDTO

data class PastaDTO(
    val id: Int,
    val nombre: String,
    val precio: Double,
    val ingredientes: List<IngredienteDTO>
)
