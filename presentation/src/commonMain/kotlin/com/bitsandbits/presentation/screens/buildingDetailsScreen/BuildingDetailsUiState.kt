package com.bitsandbits.presentation.screens.buildingDetailsScreen

import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.common.uiState.FloorDetailsUiState
import com.bitsandbits.presentation.common.uiState.FloorUiState

data class BuildingDetailsUiState(
    val building: BuildingUiState = BuildingUiState(),
    val floors: List<FloorUiState> = emptyList(),
    val selectedFloorDetailsUiState: FloorDetailsUiState? = null
)