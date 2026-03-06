package com.bitsandbits.repository

import com.bitsandbits.entity.Location
import com.bitsandbits.entity.Point
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
interface LocationRepository {
    suspend fun getLocationsByName(name: String): List<Location>
    suspend fun downloadImage(url: String? = null): ByteArray
    suspend fun getRouteToLocation(start: Point, locationId: String): Pair<List<Point>, List<Point>>
    suspend fun getRouteBetweenTwoPoints(start: Point, end: Point): List<Point>
}