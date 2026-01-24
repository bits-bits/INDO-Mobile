package com.bitsandbits.data.repository

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLAuthorizationStatusDenied
import platform.CoreLocation.kCLAuthorizationStatusRestricted
import platform.CoreLocation.kCLLocationAccuracyBestForNavigation
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual fun getLocation(context: Any?): Flow<Pair<Double, Double>> = callbackFlow {

    // Persistent manager
    val manager = CLLocationManager().apply {
        desiredAccuracy = kCLLocationAccuracyBestForNavigation
        pausesLocationUpdatesAutomatically = false
//        requestWhenInUseAuthorization()
    }

    manager.requestWhenInUseAuthorization()

    val status = CLLocationManager.authorizationStatus()
    if (status == kCLAuthorizationStatusDenied || status == kCLAuthorizationStatusRestricted) {
        close(Exception("location permission denied"))
    }

    // Persistent delegate
    val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {
        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            val location = didUpdateLocations.lastOrNull() as? CLLocation ?: return
            val latLng = location.coordinate.useContents { Pair(latitude, longitude) }
            println("TAG BOB IN IOS GET LOCATION DATA: $latLng")
            trySend(latLng).isSuccess
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            // optional: handle errors
        }
    }

    val delegateRef: CLLocationManagerDelegateProtocol = delegate
    manager.delegate = delegateRef
    manager.startUpdatingLocation()


    awaitClose {
        manager.stopUpdatingLocation()
        manager.delegate = null
    }
}