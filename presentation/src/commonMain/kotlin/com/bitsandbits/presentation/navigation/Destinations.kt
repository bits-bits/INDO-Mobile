package com.bitsandbits.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destinations{
    @Serializable
    data object HomeScreen: Destinations

    @Serializable
    data object SeeAllScreen: Destinations
}