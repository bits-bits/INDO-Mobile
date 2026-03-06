package com.bitsandbits.presentation.screens.mapScreen

import com.bitsandbits.presentation.Base.ErrorState

data class MapUiState(
    val isLoading: Boolean = false,
    val errorState: ErrorState? = null,
    val points: List<PointUiState> = emptyList(),
    val startPoint: PointUiState? = null,
    val currentPositionPoint: PointUiState? = null,
    val initialUserPosition: PointUiState? = null,
    val usedCheckPoints: List<PointUiState> = emptyList(),
    val endPoint: PointUiState? = null,
    val destinationLocationId: String? = null
)

data class PointUiState(
    val latitude: Double,
    val longitude: Double,
    val type: PointType
)

enum class PointType {
    START_POINT,
    ENTRY_POINT,
    CHECK_POINT,
    STAIRS,
    DESTINATION,
    PROJECTION
}