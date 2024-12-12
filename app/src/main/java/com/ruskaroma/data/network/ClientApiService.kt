package com.ruskaroma.data.network

import com.ruskaroma.data.model.ClientDTO
import com.ruskaroma.data.model.LoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ClientApiService {
    @POST("/clientes/registro")
    suspend fun registerClient(@Body cliente: ClientDTO): ClientDTO
    @POST("/clientes/login")
    suspend fun loginCliente(@Body loginDTO: LoginDTO): ClientDTO
    @PUT("/clientes/actualizar")
    suspend fun updateCliente(@Body cliente: ClientDTO): ClientDTO
    @GET("/clientes")
    suspend fun getUsers(): Response<List<ClientDTO>>
}