package com.bitsandbits.presentation.screens.mapScreen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.bitsandbits.presentation.common.uiState.LocationUiState
import com.bitsandbits.presentation.navigation.Destinations

interface MapArgs {
    val latitude: Double?
    val longitude: Double?
    val locationId: String?
    val locationName: String?
    val floorNumber: Int?
}

class MapArgsImpl(savedStateHandle: SavedStateHandle): MapArgs {

    private val route: Destinations.MapScreenRoute? =
        runCatching {
            savedStateHandle.toRoute<Destinations.MapScreenRoute>()
        }.getOrNull()
    override val latitude: Double? = route?.latitude
    override val longitude: Double? = route?.longitude
    override val locationId: String? = route?.locationId
    override val floorNumber: Int? = route?.floorNumber
    override val locationName: String? = route?.locationName
}