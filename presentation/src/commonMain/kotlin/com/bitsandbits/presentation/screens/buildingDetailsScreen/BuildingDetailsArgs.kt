package com.bitsandbits.presentation.screens.buildingDetailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.bitsandbits.presentation.navigation.Destinations

interface BuildingDetailsArgs {
    val buildingId: String
    val buildingName: String
}

class BuildingDetailsArgsImpl(savedStateHandle: SavedStateHandle) : BuildingDetailsArgs {
    override val buildingId: String = savedStateHandle.toRoute<Destinations.BuildingDetailsScreenRoute>().id
    override val buildingName: String = savedStateHandle.toRoute<Destinations.BuildingDetailsScreenRoute>().name
}