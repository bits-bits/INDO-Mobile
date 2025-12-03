package com.bitsandbits.data.repository

import com.bitsandbits.data.remote.api.FloorApiService
import com.bitsandbits.data.remote.mapper.toDomain
import com.bitsandbits.entity.FloorDetails
import com.bitsandbits.repository.FloorRepository

class FloorRepositoryImpl(private val floorApiService: FloorApiService): FloorRepository {
    override suspend fun getFloorDetailsById(floorId: String): FloorDetails {
        return floorApiService.getFloorDetailsById(floorId = floorId).toDomain()
    }
}