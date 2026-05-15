package com.bitsandbits.presentation.navigation

import com.bitsandbits.presentation.common.uiState.LocationUiState
import kotlinx.serialization.Serializable

sealed interface Destinations {

    @Serializable
    data object SplashScreenRoute : Destinations

    @Serializable
    data object OnBoardingScreenRoute : Destinations

    @Serializable
    data object LoginScreenRoute : Destinations

    @Serializable
    data object HomeScreenRoute : Destinations

    @Serializable
    data class MapScreenRoute(val latitude: Double?, val longitude: Double?, val locationId: String?, val floorNumber: Int?, val locationName: String?) : Destinations

    @Serializable
    data object LibraryScreenRoute : Destinations

    @Serializable
    data object ProfileScreeRoute : Destinations

    @Serializable
    data class BuildingDetailsScreenRoute(val id: String, val name: String) : Destinations
}

sealed interface MainScreenDestinations {
    @Serializable
    data object Home : MainScreenDestinations

    @Serializable
    data object Map : MainScreenDestinations

    @Serializable
    data object Library : MainScreenDestinations

    @Serializable
    data object Profile : MainScreenDestinations
}

val MainScreenDestinations.index: Int
    get() = when (this) {
        MainScreenDestinations.Home -> 0
        MainScreenDestinations.Map -> 1
        MainScreenDestinations.Library -> 2
        MainScreenDestinations.Profile -> 3
    }