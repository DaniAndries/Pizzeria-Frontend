package com.ruskaroma.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://pizzeriarest.onrender.com/"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val clientApi: ClientApiService by lazy {
        retrofit.create(ClientApiService::class.java)
    }
    val productApi: ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}