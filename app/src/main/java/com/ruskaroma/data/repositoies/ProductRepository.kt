package com.ruskaroma.data.repositoies

import com.ruskaroma.data.model.LoginDTO
import com.ruskaroma.data.model.ProductDTO
import com.ruskaroma.data.network.ProductApiService

class ProductRepository (private val apiService: ProductApiService){
    suspend fun getProducts(): Result<List<ProductDTO>> {
        return try {
            val response = apiService.getProducts()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}