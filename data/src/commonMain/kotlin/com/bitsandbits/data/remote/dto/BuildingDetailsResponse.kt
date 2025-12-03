package com.bitsandbits.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BuildingDetailsResponse(
    @SerialName("floorsBasicDetails")
    val floorsBasicDetailResponses: List<FloorResponse>,
    @SerialName("id")
    val id: String,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("name")
    val name: String
)