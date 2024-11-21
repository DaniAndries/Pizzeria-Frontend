package com.composePizzeria.data

data class IngredientDTO(
    val id:Int,
    val name:String,
    val alergens:List<String>
)
