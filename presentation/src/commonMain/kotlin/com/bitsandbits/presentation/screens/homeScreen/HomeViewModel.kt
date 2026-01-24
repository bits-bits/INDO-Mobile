package com.bitsandbits.presentation.screens.homeScreen

import androidx.lifecycle.viewModelScope
import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.presentation.Base.ErrorState
import com.bitsandbits.presentation.common.mapper.toBuildingUiState
import com.bitsandbits.presentation.common.mapper.toLocationUiState
import com.bitsandbits.repository.BuildingsRepository
import com.bitsandbits.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val locationRepository: LocationRepository,
    private val buildingsRepository: BuildingsRepository
) :
    BaseViewModel<HomeUiState, Nothing>(HomeUiState()), HomeInteractionListener {

    val searchQueryFlow = MutableStateFlow("")
    init {
        loadData()
    }

    fun loadData(){
        getAllBuildings()
        observeSearchQuery()
    }

    private fun getAllBuildings() {
        setBuildingsLoading()
        println("TAG JOE , BUILDINGS vm FROM DOMAIN: is loading${state.value.buildingsTab.isLoading}")
        tryToExecute(
            function = { buildingsRepository.getAllBuildings() },
            onSuccess = { buildings ->
                println("TAG JOE , BUILDINGS CAM FROM DOMAIN: $buildings")
                updateState {
                    it.copy(
                        buildingsTab = it.buildingsTab.copy(buildings = buildings.map { it.toBuildingUiState() })
                    )
                }
                setBuildingsLoading(false)
                println("TAG JOE , BUILDINGS vm FROM DOMAIN: is loading${state.value.buildingsTab.isLoading}")
            },
            onError = {e -> onError(e)}
        )
    }

     fun searchLocation() {
         println("TAG ZOZ home onClickSearchBar onChangeQuery searchLocation, state is: ${state.value.searchTab.searchQuery}")
         tryToExecute(
            function = {
                setLocationsLoading(true)
                locationRepository.getLocationsByName(state.value.searchTab.searchQuery)
            },
            onSuccess = { locations ->
                println("TAG bob, locations: $locations")
                updateState {
                    it.copy(
                        it.searchTab.copy(
                            locations = locations.map { it.toLocationUiState() },
                            showNumberOfResults = true
                        )
                    )
                }
                setLocationsLoading(false)
            },
            onError = { e ->
                setLocationsLoading(false)
                onError(e)
            },
        )
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(DEBOUNCE_TIME)
                .filter { it.isNotEmpty() }
                .distinctUntilChanged()
                .collectLatest { searchLocation() }
        }
    }

    fun setBuildingsLoading(isLoading: Boolean = true){
        updateState { it.copy(buildingsTab = it.buildingsTab.copy(isLoading = isLoading)) }
    }

    override fun onChangeQuery(query: String) {
        println("TAG ZOZ home onClickSearchBar onChangeQuery: query is $query, state is: ${state.value.searchTab.searchQuery}")
        searchQueryFlow.value = query
        updateState { it.copy(it.searchTab.copy(searchQuery = query)) }
    }

    override fun onClickClearQuery() {
        onChangeQuery("")
        updateState { it.copy(searchTab = it.searchTab.copy(locations = emptyList(), showNumberOfResults = false)) }
        onClickScreen()
    }

    override fun onClickSearchBar() {
        println("TAG ZOZ home onClickSearchBar")
        println("TAG FOCUS CHANGED before in view model search is: ${state.value.showSearchLayout} building is: ${state.value.showBuildingsLayout}")
        updateState { it.copy(showBuildingsLayout = false, showSearchLayout = true) }
        println("TAG FOCUS CHANGED after in view model search is: ${state.value.showSearchLayout} building is: ${state.value.showBuildingsLayout}")
    }

    override fun onClickScreen() {
        updateState { it.copy(showSearchLayout = false, showBuildingsLayout = true) }
    }

    private fun setLocationsLoading(isLoading: Boolean) {
        updateState { it.copy(it.searchTab.copy(isLoading = isLoading)) }
    }

    private fun onError(e: ErrorState) {
        println("TAG bob, locations: ${e.message}")
        updateState { it.copy(it.searchTab.copy(errorMessage = e.message)) }
        updateState { it.copy(buildingsTab = it.buildingsTab.copy(isLoading = false)) }
    }

    companion object {
        const val DEBOUNCE_TIME = 1000L
    }
}

