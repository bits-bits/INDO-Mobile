package com.bitsandbits.data.remote.api

import com.bitsandbits.data.remote.dto.LocationDetailsResponse
import com.bitsandbits.data.remote.dto.RouteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class LocationApiService(private val client: HttpClient = NetworkClient.client) {
    suspend fun getLocationsByName(query: String): List<LocationDetailsResponse> {
        return client.get("/api/v1/location/by_name") {
            parameter("name", query)
        }.body<List<LocationDetailsResponse>>()
    }

    suspend fun getRouteBetweenTwoPoints(
        startLat: Double,
        startLong: Double,
        endLat: Double,
        endLong: Double
    ): RouteResponse {
        return client.get("/api/v1/route") {
            parameter("from", "$startLat,$startLong")
            parameter("to", "$endLat,$endLong")
        }.body<RouteResponse>()
    }

    suspend fun getRouteToLocation(
        startLat: Double,
        startLong: Double,
        locationId: String
    ): List<RouteResponse> {
        return client.get("/api/v1/route/location") {
            parameter("from", "$startLat,$startLong")
            parameter("toLocationId", locationId)
        }.body<List<RouteResponse>>()
    }
}