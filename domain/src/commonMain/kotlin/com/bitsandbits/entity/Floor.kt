package com.bitsandbits.entity

data class Floor(
    val id: String,
    val number: Int,
)

data class FloorDetails(
    val id: String,
    val number: Number,
    val imageUrl: String?,
    val locations: List<Location>
)
