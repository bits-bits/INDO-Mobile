package com.bitsandbits.presentation.screens.HomeScreen

import com.bitsandbits.entity.Building
import com.bitsandbits.entity.Location

data class HomeUiState(
    val searchTab: SearchTab = SearchTab(),
    val buildingsTab: BuildingsTab = BuildingsTab(),
    val tempImage: ByteArray? = null,
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
        val buildings: List<Building> = emptyList()
    )
}
