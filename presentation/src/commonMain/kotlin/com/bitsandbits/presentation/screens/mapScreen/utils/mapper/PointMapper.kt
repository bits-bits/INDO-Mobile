package com.bitsandbits.presentation.screens.mapScreen.utils.mapper

import com.bitsandbits.entity.CoordinateType
import com.bitsandbits.entity.Point
import com.bitsandbits.presentation.screens.mapScreen.PointType
import com.bitsandbits.presentation.screens.mapScreen.PointUiState

fun Point.toPointUiState(): PointUiState {
    return PointUiState(
        latitude = this.latitude,
        longitude = this.longitude,
        type = this.type.toPointType()
    )
}

fun CoordinateType.toPointType(): PointType {
    return when (this) {
        CoordinateType.START_POINT -> PointType.START_POINT
        CoordinateType.ENTRY_POINT -> PointType.ENTRY_POINT
        CoordinateType.CHECK_POINT -> PointType.CHECK_POINT
        CoordinateType.STAIRS -> PointType.STAIRS
        CoordinateType.DESTINATION -> PointType.DESTINATION
        CoordinateType.PROJECTION -> PointType.PROJECTION
    }
}

fun PointType.toCoordinateType(): CoordinateType {
    return when (this) {
        PointType.START_POINT -> CoordinateType.START_POINT
        PointType.ENTRY_POINT -> CoordinateType.ENTRY_POINT
        PointType.CHECK_POINT -> CoordinateType.CHECK_POINT
        PointType.STAIRS -> CoordinateType.STAIRS
        PointType.DESTINATION -> CoordinateType.DESTINATION
        PointType.PROJECTION -> CoordinateType.PROJECTION
    }
}