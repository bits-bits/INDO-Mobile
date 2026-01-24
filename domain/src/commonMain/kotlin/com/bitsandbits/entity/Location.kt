package com.bitsandbits.entity

data class Location(
    val id: String,
    val name: String,
    val aliasName: String?,
    val floorNumber: Int,
    val buildingName: String,
    val floorImageUrl: String? = null,
    val latitude: Double?,
    val longitude: Double?
)
