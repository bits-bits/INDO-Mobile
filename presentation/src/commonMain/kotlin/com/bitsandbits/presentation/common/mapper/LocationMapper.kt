package com.bitsandbits.presentation.common.mapper

import com.bitsandbits.entity.Location
import com.bitsandbits.presentation.common.uiState.LocationUiState

fun Location.toLocationUiState(): LocationUiState {
    return LocationUiState(
        id = this.id,
        name = this.name,
        aliasName = this.aliasName,
        imageUrl = this.floorImageUrl,
        buildingName = this.buildingName,
        floorNumber = this.floorNumber
    )
}