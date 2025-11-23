package com.bitsandbits.repository

import com.bitsandbits.entity.Building
import com.bitsandbits.entity.BuildingDetails

interface BuildingsRepository {
    suspend fun getAllBuildings(): List<Building>
    suspend fun getBuildingDetailsById(id: String): BuildingDetails
}