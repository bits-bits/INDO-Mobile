package com.bitsandbits.presentation.common.mapper

import com.bitsandbits.entity.Floor
import com.bitsandbits.presentation.common.uiState.FloorUiState

fun Floor.toFloorUiState(): FloorUiState {
    return FloorUiState(
        floorId = this.id,
        floorNumber = this.number,
        imageUrl = this.imageUrl
    )
}