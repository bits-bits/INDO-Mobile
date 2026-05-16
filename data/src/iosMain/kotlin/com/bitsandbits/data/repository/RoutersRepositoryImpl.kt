package com.bitsandbits.data.repository

import com.bitsandbits.entity.Router
import com.bitsandbits.repository.RoutersRepository
import kotlinx.coroutines.flow.Flow

class RoutersRepositoryImpl: RoutersRepository {
    override fun getNearbyRouters(): Flow<List<Router>> {
        TODO("Not yet implemented")
    }

}