package com.bitsandbits.presentation.screens.HomeScreen

import androidx.lifecycle.viewModelScope
import com.bitsandbits.entity.Location
import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.presentation.Base.ErrorState
import com.bitsandbits.repository.LocationRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val locationRepository: LocationRepository) :
    BaseViewModel<HomeUiState, Nothing>(HomeUiState()), HomeInteractionListener {

    override fun onClickSearch() {
        tryToExecute(
            function = {
                setLoading(true)
                locationRepository.getLocationsByName(state.value.searchTab.searchQuery)
            },
            onSuccess = { locations ->
                println("TAG bob, locations: $locations")
                updateState {
                    it.copy(
                        it.searchTab.copy(
                            locations = locations,
                            showNumberOfResults = true
                        )
                    )
                }
                setLoading(false)
            },
            onError = { e ->
                setLoading(false)
                onError(e)
            },
        )
    }

    override fun onChangeQuery(query: String) {
        updateState { it.copy(it.searchTab.copy(searchQuery = query)) }
    }

    override fun onClickSearchBar() {
        updateState { it.copy(showBuildingsLayout = false, showSearchLayout = true) }
    }

    override fun onClickScreen() {
        updateState { it.copy(showSearchLayout = false, showBuildingsLayout = true) }
    }

    private fun setLoading(isLoading: Boolean) {
        updateState { it.copy(it.searchTab.copy(isLoading = isLoading)) }
    }

    private fun onError(e: ErrorState) {
        println("TAG bob, locations: ${e.message}")
        updateState { it.copy(it.searchTab.copy(errorMessage = e.message)) }
    }

    fun getFakeLocations(): List<Location> {
        return listOf(
            Location(
                id = "212",
                name = state.value.searchTab.searchQuery,
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"
            ),
            Location(
                id = "212",
                name = "m220",
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"

            ),
            Location(
                id = "212",
                name = "shimy",
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"
            ),
            Location(
                id = "212",
                name = "c102",
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"

            ),
            Location(
                id = "212",
                name = "m220",
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"
            ),
            Location(
                id = "212",
                name = "shimy",
                aliasName = "h301",
                floorNumber = 2,
                buildingName = "ssp"
            ),
        )
    }
}

