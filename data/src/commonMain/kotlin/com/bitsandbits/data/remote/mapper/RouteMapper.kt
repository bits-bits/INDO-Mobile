package com.bitsandbits.data.remote.mapper

import com.bitsandbits.data.remote.dto.CoordinateResponse
import com.bitsandbits.entity.CoordinateType
import com.bitsandbits.entity.Point

fun CoordinateResponse.toDomain(): Point {
    return Point(
        latitude = this.latitude,
        longitude = this.longitude,
        type = this.type.toCoordinateType()
    )
}

fun String.toCoordinateType(): CoordinateType {
    return when (this) {
        "startPoint" -> CoordinateType.START_POINT
        "entryPoint" -> CoordinateType.ENTRY_POINT
        "checkPoint" -> CoordinateType.CHECK_POINT
        "stairs" -> CoordinateType.STAIRS
        "destination" -> CoordinateType.DESTINATION
        "projection" -> CoordinateType.PROJECTION
        else -> throw IllegalArgumentException("Unknown coordinate type: $this")
    }
}