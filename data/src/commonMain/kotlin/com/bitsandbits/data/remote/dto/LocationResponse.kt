package com.bitsandbits.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String?,
    @SerialName("aliasName")
    val aliasName: String?
)