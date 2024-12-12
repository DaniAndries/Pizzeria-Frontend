package com.ruskaroma.data.repositoies

import com.ruskaroma.data.model.ClientDTO
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
}