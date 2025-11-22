package com.bitsandbits.data.remote.api

import com.bitsandbits.data.remote.dto.BuildingDetailsResponse
import com.bitsandbits.data.remote.dto.BuildingResponse
import com.bitsandbits.entity.BuildingDetails
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BuildingApiService(private val client: HttpClient = NetworkClient.client) {
    suspend fun getAllBuildings(): List<BuildingResponse> {
        return client.get("/api/v1/$GET_ALL_BUILDINGS_ENDPOINT").body<List<BuildingResponse>>()
    }

    suspend fun getBuildingDetailsById(id: String): BuildingDetailsResponse {
        return client.get("/api/v1$GET_BUILDING_BY_ID_ENDPOINT/$id").body<BuildingDetailsResponse>()
    }

    private companion object {
        private const val  GET_ALL_BUILDINGS_ENDPOINT = "/building/all"
        private const val  GET_BUILDING_BY_ID_ENDPOINT = "/building"
    }
}