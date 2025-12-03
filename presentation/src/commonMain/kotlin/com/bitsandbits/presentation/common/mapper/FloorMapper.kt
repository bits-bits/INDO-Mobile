package com.bitsandbits.presentation.common.mapper

import com.bitsandbits.entity.Floor
import com.bitsandbits.entity.FloorDetails
import com.bitsandbits.presentation.common.uiState.FloorDetailsUiState
import com.bitsandbits.presentation.common.uiState.FloorUiState

fun Floor.toFloorUiState(): FloorUiState {
    return FloorUiState(
        floorId = this.id,
        floorNumber = this.number,
        imageUrl = this.imageUrl
    )
}

fun FloorDetails.toFloorDetailsUiState(): FloorDetailsUiState {
    return FloorDetailsUiState(
        floorId = this.id,
        floorNumber = this.number.toInt(),
        imageUrl = this.imageUrl,
        buildingName = null,
        locations = this.locations.map { it.toLocationUiState() }
    )
}