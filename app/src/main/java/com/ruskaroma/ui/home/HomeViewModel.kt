package com.ruskaroma.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruskaroma.data.model.ORDER_STATUS
import com.ruskaroma.data.model.OrderDTO
import com.ruskaroma.data.model.OrderLineDTO
import com.ruskaroma.data.model.ProductDTO
import com.ruskaroma.data.repositoies.ClientRepository
import com.ruskaroma.data.repositoies.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Date
import java.util.Locale

/**
 * ViewModel responsible for managing the state and business logic of the Home screen.
 */
class HomeViewModel(private val productRepository: ProductRepository) : ViewModel() {

    /** A live data object holding the list of products available on the home screen. */
    val productList = MutableLiveData<List<ProductDTO>>()

    /** A live data object holding the current order details. */
    val order = MutableLiveData<OrderDTO>()

    /** A live data object holding the total number of products added to the cart. */
    val totalProducts = MutableLiveData(0)

    /** A DecimalFormat instance to format prices to two decimal places. */
    val format: DecimalFormat = DecimalFormat("#.00").apply {
        decimalFormatSymbols = DecimalFormatSymbols(Locale.US)
    }

    init {
        viewModelScope.launch {
                val result = productRepository.getProducts()
                withContext(Dispatchers.Main) {
                    when (result.isSuccess) {
                        true -> {
                            productList.value = result.getOrThrow()
                        }
                        false -> {
                            Log.d("Login", "Error:$result")
                        }
                    }
                }
            }
    }

    /**
     * Adds a product to the cart with the specified amount.
     *
     * @param amount The quantity of the product to add.
     * @param product The product to be added to the cart.
     */
    fun onAddCart(amount: Int, product: ProductDTO) {
        if (order.value == null) {
            initializeOrder()
        }

        val orderLine = OrderLineDTO(1, amount, product)

        order.value?.orderLineDTO?.add(orderLine)
        order.value?.totalPrice?.plus(product.price * amount)?.also {
            order.value?.totalPrice = truncateToTwoDecimalsMath(it)
        }

        totalProducts.value = totalProducts.value?.plus(amount)

        Log.d("Linea de pedido: ", orderLine.toString())
        Log.d("Pedido: ", order.value.toString())
    }


    /**
     * Initializes a new order when none exists.
     */
    private fun initializeOrder() {
        order.value = OrderDTO(1, Date(), ORDER_STATUS.PENDING, 0.0, mutableListOf())
    }

    /**
     * Truncates a double value to two decimal places using mathematical operations.
     *
     * @param price The value to be truncated.
     * @return The truncated value with two decimal places.
     */
    private fun truncateToTwoDecimalsMath(price: Double): Double {
        return (price * 100).toInt() / 100.0
    }


}