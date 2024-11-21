package com.ruskaroma.data

import com.composePizzeria.data.IngredientDTO
import com.composePizzeria.data.MEAT_TYPE

class ProductDTO(
    val id: Int,
    val name: String,
    val price: Double,
    var size: SIZE? = null,
    val ingredients: List<IngredientDTO> = emptyList(),
    val type: TYPE,
    var meat: MEAT_TYPE? = null
)
