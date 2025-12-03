package com.bitsandbits.data.remote.api

import com.bitsandbits.data.remote.dto.FloorDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class FloorApiService(private val client: HttpClient = NetworkClient.client ) {
    suspend fun getFloorDetailsById(floorId: String): FloorDetailsResponse {
        return client.get("/api/v1$GET_FLOOR_DETAILS_BY_ID_ENDPOINT/$floorId").body<FloorDetailsResponse>()

    }

    private companion object {
        private const val GET_FLOOR_DETAILS_BY_ID_ENDPOINT = "/floor"
    }
}