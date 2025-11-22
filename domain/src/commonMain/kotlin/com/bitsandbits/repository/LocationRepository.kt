package com.bitsandbits.repository

import com.bitsandbits.entity.Location

interface LocationRepository {
    suspend fun getLocationsByName(name: String): List<Location>
    suspend fun downloadImage(url: String? = null): ByteArray
}