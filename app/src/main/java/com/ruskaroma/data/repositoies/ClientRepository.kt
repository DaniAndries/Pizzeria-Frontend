package com.ruskaroma.data.repositoies

import com.ruskaroma.data.model.ClientDTO
import com.ruskaroma.data.model.LoginDTO
import com.ruskaroma.data.network.ClientApiService

class ClientRepository(private val apiService: ClientApiService) {
    suspend fun registerClient(client: ClientDTO): Result<ClientDTO> {
        return try {
            val response = apiService.registerClient(client)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }

    suspend fun loginClient(client: LoginDTO): Result<LoginDTO> {
        return try {
            val response = apiService.loginCliente(client)
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)

        }
    }
}