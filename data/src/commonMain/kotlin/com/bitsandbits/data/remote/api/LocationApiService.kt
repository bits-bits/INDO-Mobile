package com.bitsandbits.data.remote.api

import com.bitsandbits.data.remote.dto.LocationResponseItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class LocationApiService(private val client: HttpClient = NetworkClient.client) {
    suspend fun getLocationsByName(query: String): List<LocationResponseItem> {
        return client.get("/api/v1/location/by_name") {
            parameter("name", query)
        }.body<List<LocationResponseItem>>()
    }
}