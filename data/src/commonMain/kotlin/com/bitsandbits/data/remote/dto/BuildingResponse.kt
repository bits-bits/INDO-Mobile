package com.bitsandbits.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BuildingResponse(
    @SerialName("id")
    val id: String,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("name")
    val name: String
)