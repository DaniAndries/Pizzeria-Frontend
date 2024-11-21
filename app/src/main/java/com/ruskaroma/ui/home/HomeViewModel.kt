package com.ruskaroma.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.composePizzeria.data.IngredientDTO
import com.composePizzeria.data.ORDER_STATUS
import com.composePizzeria.data.OrderDTO
import com.composePizzeria.data.OrderLineDTO
import com.ruskaroma.data.ProductDTO
import com.ruskaroma.data.TYPE
import java.util.Date

class HomeViewModel {
    val productList = MutableLiveData<List<ProductDTO>>()
    val order = MutableLiveData<OrderDTO>()
    val totalProducts = MutableLiveData(0)


    fun onAddCart(amount: Int, product: ProductDTO) {
        if (order.value == null) {
            initializeOrder()
        }

        val orderLine = OrderLineDTO(1, amount, product)

        Log.d("Linea de pedido: ", orderLine.toString())
        Log.d("Pedido: ", order.value.toString())

        order.value?.orderLineDTO?.add(orderLine)
        totalProducts.value = totalProducts.value?.plus(amount)
    }

    private fun initializeOrder() {
        order.value = OrderDTO(1, Date(), ORDER_STATUS.PENDING, 0.0, mutableListOf())
    }


    // Example IngredientDTO instances with allergens
    val tomato = IngredientDTO(1, "Tomato", listOf("None"))
    val mozzarella = IngredientDTO(2, "Mozzarella", listOf("Milk"))
    val mushrooms = IngredientDTO(3, "Mushrooms", listOf("None"))
    val basil = IngredientDTO(4, "Basil", listOf("None"))
    val lettuce = IngredientDTO(5, "Lettuce", listOf("None")) // Not common for pasta but kept for reference
    val cheddar = IngredientDTO(6, "Cheddar", listOf("Milk")) // Less common for pasta, optional
    val bacon = IngredientDTO(7, "Bacon", listOf("None"))
    val onions = IngredientDTO(8, "Onions", listOf("None"))
    val cucumber = IngredientDTO(9, "Cucumber", listOf("None")) // Not typical for pasta
    val peppers = IngredientDTO(10, "Peppers", listOf("None"))
    val pickles = IngredientDTO(11, "Pickles", listOf("None")) // Not common for pasta
    val garlic = IngredientDTO(12, "Garlic", listOf("None"))
    val parmesan = IngredientDTO(13, "Parmesan", listOf("Milk"))
    val oliveOil = IngredientDTO(14, "Olive Oil", listOf("None"))
    val bbqSauce = IngredientDTO(15, "BBQ Sauce", listOf("None")) // Rare for pasta

    // 4 Pizzas without meat
    val pizza1 = ProductDTO(
        id = 1,
        name = "Margherita",
        price = 8.99,
        size = null,
        ingredients = listOf(tomato, mozzarella, basil),
        type = TYPE.PIZZA,
        meat = null
    )

    val pizza2 = ProductDTO(
        id = 2, name = "Four Cheese", price = 10.99, size = null, ingredients = listOf(
            mozzarella,
            cheddar,
            IngredientDTO(12, "Blue Cheese", listOf("Milk")),
            IngredientDTO(13, "Parmesan", listOf("Milk"))
        ), type = TYPE.PIZZA, meat = null
    )

    val pizza3 = ProductDTO(
        id = 3,
        name = "Vegetarian",
        price = 11.49,
        size = null,
        ingredients = listOf(tomato, mozzarella, mushrooms, peppers, basil),
        type = TYPE.PIZZA,
        meat = null
    )

    val pizza4 = ProductDTO(
        id = 4,
        name = "Hawaiian without Ham",
        price = 9.99,
        size = null,
        ingredients = listOf(tomato, mozzarella, IngredientDTO(14, "Pineapple", listOf("None"))),
        type = TYPE.PIZZA,
        meat = null
    )

    // 4 Pasta dishes
    val pasta1 = ProductDTO(
        id = 5,
        name = "Classic Spaghetti",
        price = 7.99,
        size = null,
        ingredients = listOf(tomato, garlic, basil, oliveOil),
        type = TYPE.PASTA,
        meat = null
    )

    val pasta2 = ProductDTO(
        id = 6,
        name = "Vegetarian Alfredo",
        price = 8.49,
        size = null,
        ingredients = listOf(mozzarella, mushrooms, parmesan, oliveOil),
        type = TYPE.PASTA,
        meat = null
    )

    val pasta3 = ProductDTO(
        id = 7,
        name = "Cheesy Penne",
        price = 8.99,
        size = null,
        ingredients = listOf(mozzarella, parmesan, garlic, oliveOil),
        type = TYPE.PASTA,
        meat = null
    )

    val pasta4 = ProductDTO(
        id = 8,
        name = "Spicy Arrabbiata",
        price = 9.49,
        size = null,
        ingredients = listOf(tomato, garlic, peppers, basil),
        type = TYPE.PASTA,
        meat = null
    )

    // 4 Drinks without ingredients or meat
    val drink1 = ProductDTO(
        id = 9,
        name = "Coca Cola",
        price = 1.99,
        size = null,
        ingredients = emptyList(),
        type = TYPE.DRINK,
        meat = null
    )

    val drink2 = ProductDTO(
        id = 10,
        name = "Orange Juice",
        price = 2.49,
        size = null,
        ingredients = emptyList(),
        type = TYPE.DRINK,
        meat = null
    )

    val drink3 = ProductDTO(
        id = 11,
        name = "Mineral Water",
        price = 1.49,
        size = null,
        ingredients = emptyList(),
        type = TYPE.DRINK,
        meat = null
    )

    val drink4 = ProductDTO(
        id = 12,
        name = "Lemonade",
        price = 2.29,
        size = null,
        ingredients = emptyList(),
        type = TYPE.DRINK,
        meat = null
    )

    // 4 Kebabs with ingredients and meat
    val kebab1 = ProductDTO(
        id = 13,
        name = "Classic Kebab",
        price = 7.99,
        size = null,
        ingredients = listOf(tomato, lettuce, onions, cucumber),
        type = TYPE.KEBAB,
        meat = null
    )

    val kebab2 = ProductDTO(
        id = 14,
        name = "BBQ Kebab",
        price = 8.49,
        size = null,
        ingredients = listOf(tomato, lettuce, onions, peppers, bbqSauce),
        type = TYPE.KEBAB,
        meat = null
    )

    val kebab3 = ProductDTO(
        id = 15,
        name = "Spicy Kebab",
        price = 9.99,
        size = null,
        ingredients = listOf(tomato, lettuce, onions, cucumber, IngredientDTO(16, "Spicy Sauce", listOf("None"))),
        type = TYPE.KEBAB,
        meat = null
    )

    val kebab4 = ProductDTO(
        id = 16, name = "Falafel Kebab", price = 8.99, size = null, ingredients = listOf(
            tomato, lettuce, peppers
        ), type = TYPE.KEBAB, meat = null
    )

    // List of all ProductDTO instances (pizzas, burgers, drinks, and kebabs)
    val allProducts = listOf(
        // Pizzas
        pizza1, pizza2, pizza3, pizza4,

        // Burgers
        pasta1, pasta2, pasta3, pasta4,

        // Drinks
        drink1, drink2, drink3, drink4,

        // Kebabs
        kebab1, kebab2, kebab3, kebab4
    )

    fun generateList() {
        // Set the allProducts list as the value for productList
        productList.value = allProducts
    }

}