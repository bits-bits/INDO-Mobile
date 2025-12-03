package com.bitsandbits.presentation.screens.buildingDetailsScreen

import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.presentation.Base.ErrorState
import com.bitsandbits.presentation.common.mapper.toBuildingDetailsUiState
import com.bitsandbits.presentation.common.mapper.toFloorDetailsUiState
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.common.uiState.FloorDetailsUiState
import com.bitsandbits.presentation.common.uiState.LocationUiState
import com.bitsandbits.repository.BuildingsRepository
import com.bitsandbits.repository.FloorRepository

class BuildingDetailsViewModel(
    private val buildingsRepository: BuildingsRepository,
    private val floorRepository: FloorRepository,
    private val args: BuildingDetailsArgs
) : BaseViewModel<BuildingDetailsUiState, BuildingDetailsEffect>(BuildingDetailsUiState()),
    BuildingDetailsInteractionListener {
    init {
        loadBuilding()
    }

    fun loadBuilding() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            function = { buildingsRepository.getBuildingDetailsById(id = args.buildingId) },
            onSuccess = { buildingDetails ->
                updateState { buildingDetails.toBuildingDetailsUiState() }
                updateState { it.copy(isLoading = false) }
                println("TAG BOB, SELECTED FLOR IS: ${state.value.selectedFloorDetailsUiState}")
            },
            onError = { e -> onError(e) }
        )
    }

    fun setSelectedFloor(floorId: String? = state.value.floors[0].floorId) {
        if (floorId == null || state.value.selectedFloorDetailsUiState?.floorId == floorId) return
        else{
            setLocationsLoading()
            println("TAG, LOCATIONS LOADING: ${state.value.isFloorLocationsLoading}")
            tryToExecute(
                function = { floorRepository.getFloorDetailsById(floorId = floorId) },
                onSuccess = { floorDetails ->
                    updateState { it.copy(selectedFloorDetailsUiState = floorDetails.toFloorDetailsUiState().copy(buildingName = state.value.building.name)) }
                    updateSelectedFloorLocations()
                    setLocationsLoading(false)
                },
                onError = { e -> onError(e) }
            )
        }
    }

    private fun updateSelectedFloorLocations(){
        val selectedFloor = state.value.selectedFloorDetailsUiState
        val locations = state.value.selectedFloorDetailsUiState?.locations?.map { it.copy(buildingName = state.value.building.name, floorNumber = selectedFloor?.floorNumber) }
        updateState { it.copy(selectedFloorDetailsUiState = it.selectedFloorDetailsUiState?.copy(locations = locations!!))}
    }

    private fun onError(error: ErrorState) {
        println("TAG BOB, IN view model load building details on error")
        updateState { it.copy(isLoading = true) }
        // TODO: HANDLE ERROR
    }

    override fun onClickFloor(floorId: String) {
        println("TAG BOB, IN view model on click floor: $floorId")
        setSelectedFloor(floorId = floorId)
    }

    override fun onClickLocation() {
        TODO("Not yet implemented")
    }

    override fun onClickSeeAllFloors() {
        TODO("Not yet implemented")
    }

    override fun onClickSeeAllLocations() {
        TODO("Not yet implemented")
    }

    override fun onClickBack() {
        println("hellooooo vm")
        emitNewEffect(BuildingDetailsEffect.NavigateBack)
    }

    private fun setLocationsLoading(isLoading: Boolean = true){
        updateState { it.copy(isFloorLocationsLoading = isLoading) }
    }


}