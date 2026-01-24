package com.bitsandbits.data.repository

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@SuppressLint("MissingPermission")
actual fun getLocation(context: Any?): Flow<Pair<Double, Double>> {
    val androidContext = context as Context
    val fusedClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(androidContext)

    val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,
        2000L
    ).setMinUpdateDistanceMeters(1f)
        .build()

    return callbackFlow {
        val callback  = object: LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val location = result.lastLocation ?: return
                println("TAG BOB IN ANDROID GET LOCATION DATA: ${location.latitude}, ${location.longitude}")
                trySend(Pair(location.latitude, location.longitude))
            }
        }

        fusedClient.requestLocationUpdates(locationRequest, callback, null)

        awaitClose {
            fusedClient.removeLocationUpdates(callback)
        }
    }
}

