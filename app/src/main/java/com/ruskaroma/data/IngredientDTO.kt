package com.ruskaroma.data

/**
 * Data class representing an ingredient used in the system.
 * This class holds the information about an ingredient such as its unique ID, name, and a list of allergens that it may contain.
 *
 * @param id Unique identifier for the ingredient.
 * @param name The name of the ingredient (e.g., cheese, pepperoni, etc.).
 * @param alergens A list of allergens associated with this ingredient.
 *                  Each string in the list represents a specific allergen (e.g., "gluten", "milk").
 */
data class IngredientDTO(
    /**
     * Unique identifier for the ingredient.
     * This ID can be used for referencing or linking the ingredient to other entities (e.g., orders or recipes).
     */
    val id: Int,

    /**
     * The name of the ingredient.
     * This represents the common name or description of the ingredient, such as "Cheese", "Tomato", etc.
     */
    val name: String,

    /**
     * List of allergens that the ingredient may contain.
     * Each element in the list is a string representing an allergen, such as "gluten", "milk", or "nuts".
     * This list is used to inform customers of potential allergens in dishes.
     */
    val alergens: List<String>
)
