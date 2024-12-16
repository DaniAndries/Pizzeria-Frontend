package com.ruskaroma.data.network

import com.ruskaroma.data.model.ClientDTO
import com.ruskaroma.data.model.LoginDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ClientApiService {
    @POST("/clients/register")
    suspend fun registerClient(@Body client: ClientDTO): ClientDTO
    @POST("/clients/login")
    suspend fun loginCliente(@Body loginDTO: LoginDTO): ClientDTO
    @PUT("/clients/update")
    suspend fun updateCliente(@Body client: ClientDTO): ClientDTO
    @GET("/clients")
    suspend fun getUsers(): Response<List<ClientDTO>>
}