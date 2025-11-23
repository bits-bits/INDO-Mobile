package com.bitsandbits.presentation.screens.buildingDetailsScreen

sealed interface BuildingDetailsEffect{
    data class NavigateToSeeAllLocationsScreen(val floorId: String): BuildingDetailsEffect
    data class NavigateTooSeeAllFloorsScreen(val buildingId: String): BuildingDetailsEffect
}