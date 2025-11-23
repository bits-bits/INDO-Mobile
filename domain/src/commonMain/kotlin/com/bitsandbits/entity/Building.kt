package com.bitsandbits.entity

data class Building(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String?
)

data class BuildingDetails(
    val id: String,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val longitude: Double?,
    val latitude: Double?,
    val floors: List<Floor>
)
