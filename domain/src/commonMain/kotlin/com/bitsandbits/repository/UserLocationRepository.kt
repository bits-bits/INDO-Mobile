package com.bitsandbits.repository

import kotlinx.coroutines.flow.Flow

interface UserLocationRepository {
    fun getUserLocation(): Flow<Pair<Double, Double>?>
}