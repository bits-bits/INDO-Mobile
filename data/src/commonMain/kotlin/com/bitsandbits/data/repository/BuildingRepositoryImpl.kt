package com.bitsandbits.data.repository

import com.bitsandbits.data.remote.api.BuildingApiService
import com.bitsandbits.data.remote.mapper.toDomain
import com.bitsandbits.entity.Building
import com.bitsandbits.entity.BuildingDetails
import com.bitsandbits.repository.BuildingRepository

class BuildingRepositoryImpl(private val buildingApiService: BuildingApiService) : BuildingRepository{
    override suspend fun getAllBuildings(): List<Building> {
        return buildingApiService.getAllBuildings().map { it.toDomain() }
    }

    override suspend fun getBuildingDetailsById(id: String): BuildingDetails {
        return buildingApiService.getBuildingDetailsById(id).toDomain()
    }
}