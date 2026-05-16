package com.bitsandbits.repository

import com.bitsandbits.entity.Router
import kotlinx.coroutines.flow.Flow

interface RoutersRepository {
    fun getNearbyRouters(): Flow<List<Router>>
}