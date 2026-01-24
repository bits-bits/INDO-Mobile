package com.bitsandbits.presentation.common.uiState

data class LocationUiState(
    val id: String,
    val name: String = "",
    val aliasName: String? = null,
    val imageUrl: String? = null,
    val buildingName: String? = null,
    val floorNumber: Number? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)