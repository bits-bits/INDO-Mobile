package com.bitsandbits.data.repository

import com.bitsandbits.data.remote.api.LocationApiService
import com.bitsandbits.data.remote.api.NetworkClient
import com.bitsandbits.data.remote.mapper.toDomain
import com.bitsandbits.entity.Location
import com.bitsandbits.entity.Point
import com.bitsandbits.repository.LocationRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class LocationRepositoryImpl(private val locationApiService: LocationApiService) :
    LocationRepository {
    override suspend fun getLocationsByName(name: String): List<Location> {
        return locationApiService.getLocationsByName(name).map { it.toDomain() }
    }

    override suspend fun downloadImage(url: String?): ByteArray {
        val imageUrl =
            "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
        val client = NetworkClient.client
        val response = client.get(imageUrl)
        val byteArray = response.body<ByteArray>()
        return byteArray
    }

    override suspend fun getRouteToLocation(
        start: Point,
        locationId: String
    ): Pair<List<Point>, List<Point>> {
        val response = locationApiService.getRouteToLocation(
            startLat = start.latitude,
            startLong = start.longitude,
            locationId = locationId
        )

        val totalRoute = response.first().coordinates.map { it.toDomain() }
        val checkPoints = response[1].coordinates.map { it.toDomain() }

        return Pair(totalRoute, checkPoints)
    }

    override suspend fun getRouteBetweenTwoPoints(
        start: Point,
        end: Point
    ): List<Point> {
        val response = locationApiService.getRouteBetweenTwoPoints(
            startLat = start.latitude,
            startLong = start.longitude,
            endLat = end.latitude,
            endLong = end.longitude
        ).coordinates

        return response.map { it.toDomain() }
    }


}