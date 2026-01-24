package com.bitsandbits.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RouteResponse(
    val coordinates: List<CoordinateResponse>
)

@Serializable
data class CoordinateResponse(
    val latitude: Double,
    val longitude: Double,
    val type: String
)