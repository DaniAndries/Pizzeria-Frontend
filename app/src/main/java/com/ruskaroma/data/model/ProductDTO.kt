package com.ruskaroma.data.model

/**
 * Data class representing a product in the system.
 * This class stores details about a product, including its identifier, name, price, size,
 * ingredients, type, and optional meat type.
 *
 * @property id The unique identifier for the product.
 * @property name The name of the product.
 * @property price The price of the product.
 * @property size The size of the product, which is optional (could be null).
 * @property ingredients A list of ingredients used in the product, represented by a list of [IngredientDTO].
 * @property type The type of the product, represented by the [TYPE] enum.
 * @property meat The meat type used in the product, which is optional (could be null).
 * It is defined by the [MEAT_TYPE] enum.
 */
class ProductDTO(
    val id: Int,
    val name: String,
    val price: Double,
    var size: SIZE? = null,
    val ingredients: List<IngredientDTO> = emptyList(),
    val type: TYPE,
    var meat: MEAT_TYPE? = null
)
