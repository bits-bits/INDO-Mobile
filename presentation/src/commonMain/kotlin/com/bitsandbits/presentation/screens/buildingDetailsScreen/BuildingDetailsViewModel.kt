package com.bitsandbits.presentation.screens.buildingDetailsScreen

import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.presentation.Base.ErrorState
import com.bitsandbits.presentation.common.mapper.toBuildingDetailsUiState
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.repository.BuildingsRepository

class BuildingDetailsViewModel(
    private val buildingsRepository: BuildingsRepository,
    private val args: BuildingDetailsArgs
) : BaseViewModel<BuildingDetailsUiState, BuildingDetailsEffect>(BuildingDetailsUiState()),
    BuildingDetailsInteractionListener {
    init {
        loadBuilding()
    }

    fun loadBuilding() {
        updateState { it.copy(building = BuildingUiState(name = args.buildingName)) }
        tryToExecute(
            function = { buildingsRepository.getBuildingDetailsById(id = args.buildingId) },
            onSuccess = { buildingDetails -> updateState { buildingDetails.toBuildingDetailsUiState() } },
            onError = { e -> onError(e) }
        )
    }

    private fun onError(error: ErrorState) {
        println("TAG BOB, IN view model load building details on error")
        // TODO: HANDLE ERROR
    }

    override fun onClickFloor() {
        TODO("Not yet implemented")
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


}