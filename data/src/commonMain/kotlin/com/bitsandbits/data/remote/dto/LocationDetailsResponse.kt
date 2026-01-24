package com.bitsandbits.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LocationDetailsResponse(
    @SerialName("aliasName")
    val aliasName: String?,
    @SerialName("buildingName")
    val buildingName: String,
    @SerialName("floorNumber")
    val floorNumber: Int,
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String?
)