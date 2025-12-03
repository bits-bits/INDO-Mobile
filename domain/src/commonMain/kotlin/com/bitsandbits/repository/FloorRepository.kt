package com.bitsandbits.repository

import com.bitsandbits.entity.FloorDetails

interface FloorRepository {
    suspend fun getFloorDetailsById(floorId: String): FloorDetails
}