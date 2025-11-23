package com.bitsandbits.presentation.common.uiState

data class FloorDetailsUiState(
    val floorId: String,
    val floorNumber: Int = 0,
    val imageUrl: String? = null,
    val buildingName: String? = null,
    val locations: List<LocationUiState>,
    val isSelected: Boolean = false
)