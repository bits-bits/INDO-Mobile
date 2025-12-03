package com.bitsandbits.presentation.screens.homeScreen

sealed interface HomeScreenEffect{
    data class NavigateToBuildingDetails(val buildingId: String) : HomeScreenEffect
}