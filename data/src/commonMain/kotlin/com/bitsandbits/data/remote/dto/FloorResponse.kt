package com.bitsandbits.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FloorResponse(
    @SerialName("id")
    val id: String,
    @SerialName("number")
    val number: Int,
    @SerialName("imageUrl")
    val imageUrl: String?
)