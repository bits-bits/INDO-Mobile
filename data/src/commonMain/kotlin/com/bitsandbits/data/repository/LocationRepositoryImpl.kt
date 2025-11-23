package com.bitsandbits.data.repository

import com.bitsandbits.data.remote.api.LocationApiService
import com.bitsandbits.data.remote.api.NetworkClient
import com.bitsandbits.data.remote.mapper.toDomain
import com.bitsandbits.entity.Location
import com.bitsandbits.repository.LocationRepository
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationRepositoryImpl(private val locationApiService: LocationApiService): LocationRepository {
    override suspend fun getLocationsByName(name: String): List<Location> {
        return locationApiService.getLocationsByName(name).map { it.toDomain() }
    }

    override suspend fun downloadImage(url: String?): ByteArray {
        val imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
        val client = NetworkClient.client
        val response = client.get(imageUrl)
        val byteArray = response.body<ByteArray>()
        return byteArray
    }
}