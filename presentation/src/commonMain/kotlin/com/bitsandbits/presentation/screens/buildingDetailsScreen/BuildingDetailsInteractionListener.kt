package com.bitsandbits.presentation.screens.buildingDetailsScreen

interface BuildingDetailsInteractionListener{
    fun onClickFloor(floorId: String)
    fun onClickLocation()
    fun onClickSeeAllFloors()
    fun onClickSeeAllLocations()
    fun onClickBack()
}