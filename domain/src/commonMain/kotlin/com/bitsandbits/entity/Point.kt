package com.bitsandbits.entity

data class Point(
    val latitude: Double,
    val longitude: Double,
    val type: CoordinateType
)

enum class CoordinateType {
    START_POINT,
    ENTRY_POINT,
    CHECK_POINT,
    STAIRS,
    DESTINATION,
    PROJECTION
}