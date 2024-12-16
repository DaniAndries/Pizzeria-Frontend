package com.ruskaroma.data.network

import com.ruskaroma.data.model.ProductDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("/products")
    suspend fun getProducts(): Response<List<ProductDTO>>
}