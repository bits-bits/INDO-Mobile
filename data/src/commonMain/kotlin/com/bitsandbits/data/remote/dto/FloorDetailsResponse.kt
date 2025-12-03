package com.bitsandbits.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FloorDetailsResponse(
    @SerialName("id")
    val id: String,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("number")
    val number: Int,
    @SerialName("locations")
    val locationBasicDetails: List<LocationResponse>,
)