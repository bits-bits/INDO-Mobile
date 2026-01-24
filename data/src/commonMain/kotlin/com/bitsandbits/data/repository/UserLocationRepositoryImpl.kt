package com.bitsandbits.data.repository

import com.bitsandbits.repository.UserLocationRepository
import kotlinx.coroutines.flow.Flow

expect fun getLocation(context: Any? = null): Flow<Pair<Double, Double>>

class UserLocationRepositoryImpl(private val context: Any? = null): UserLocationRepository {
    override fun getUserLocation(): Flow<Pair<Double, Double>?> {
        return getLocation(context = context)
    }
}