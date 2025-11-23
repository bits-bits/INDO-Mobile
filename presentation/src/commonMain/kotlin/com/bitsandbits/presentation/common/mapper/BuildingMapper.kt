package com.bitsandbits.presentation.common.mapper

import com.bitsandbits.entity.Building
import com.bitsandbits.entity.BuildingDetails
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsUiState

fun Building.toBuildingUiState(): BuildingUiState{
    return BuildingUiState(
        id = this.id,
        name = this.name,
        description = "",
        imageUrl = this.imageUrl
    )
}

fun BuildingDetails.toBuildingUiState(): BuildingUiState{
    return BuildingUiState(
        id = this.id,
        name = this.name,
        description = "this building is specialized in labs and electricity students are most found there !!",
        imageUrl = this.imageUrl
    )
}

fun BuildingDetails.toBuildingDetailsUiState(): BuildingDetailsUiState{
    return BuildingDetailsUiState(
        building = this.toBuildingUiState(),
        floors = this.floors.map { it.toFloorUiState() }
    )
}