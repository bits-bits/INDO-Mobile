package com.bitsandbits.presentation.screens.homeScreen

import com.bitsandbits.entity.Location
import com.bitsandbits.presentation.common.uiState.BuildingUiState

data class HomeUiState(
    val searchTab: SearchTab = SearchTab(),
    val buildingsTab: BuildingsTab = BuildingsTab(),
    val showSearchLayout: Boolean = false,
    val showBuildingsLayout: Boolean = true
    ) {
    data class SearchTab(
        val searchQuery: String = "",
        val isLoading: Boolean = false,
        val showNumberOfResults: Boolean = false,
        val errorMessage: String? = null,
        val locations: List<Location> = emptyList()
    )

    data class BuildingsTab(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val buildings: List<BuildingUiState> = emptyList()
    )
}
