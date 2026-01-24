package com.bitsandbits.presentation.screens.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.clickableWithoutRepel
import com.bitsandbits.presentation.screens.homeScreen.components.BuildingCard
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.component.LocationCard
import com.bitsandbits.presentation.navigation.Destinations
import com.bitsandbits.presentation.navigation.LocalNavController
import com.bitsandbits.presentation.screens.homeScreen.components.SearchBar
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {
    val state by homeViewModel.state.collectAsState()
    HomeScreenContent(state, homeViewModel as HomeInteractionListener)
    LaunchedEffect(Unit){
        homeViewModel.loadData()
    }
}

@Composable
fun HomeScreenContent(state: HomeUiState, interactionListener: HomeInteractionListener) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .clickableWithoutRepel(onClick = interactionListener::onClickScreen)
    ) {
        LazyColumn(contentPadding = PaddingValues(bottom = 112.dp)) {
            item {
                SearchBar(
                    value = state.searchTab.searchQuery,
                    hint = "Search . . .",
                    onValueChange = { query -> interactionListener.onChangeQuery(query = query) },
                    onClearQueryClicked = interactionListener::onClickClearQuery,
                    onFocusChange = { isFocused ->
                        if (isFocused) interactionListener.onClickSearchBar()
                    },
                    isFocused = state.showSearchLayout,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            if (state.showSearchLayout) {
                SearchLayout(state = state.searchTab)
            }
            if (state.showBuildingsLayout) {
                BuildingDetailsLayout(state = state.buildingsTab)
            }

        }
    }
}

private fun LazyListScope.SearchLayout(
    state: HomeUiState.SearchTab,
) {
    if (state.isLoading) {
        item {
            LoadingComponent(modifier = Modifier.fillParentMaxHeight())
        }
    } else {
        item {
            AnimatedVisibility(visible = state.showNumberOfResults) {
                Text(
                    text = "Total Results Found: ${state.locations.count()}",
                    style = Theme.textStyle.labelSmall,
                    color = Theme.color.secondary,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
        val locations = state.locations
        items(state.locations.count()) { locationIndex ->
            LocationCard(location = locations[locationIndex], modifier = Modifier.padding(bottom = 8.dp))
        }
    }
}

private fun LazyListScope.BuildingDetailsLayout(
    state: HomeUiState.BuildingsTab,
) {
    if (state.buildings.isEmpty()) {
        items(3) { index ->
            BuildingCard(
                building = null,
                isLoading = state.isLoading,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    } else {
        items(
            count = state.buildings.size,
            key = { index -> state.buildings[index].id }
        ) { index ->
            val navController = LocalNavController.current
            val building = state.buildings[index]
            BuildingCard(
                building = building,
                isLoading = state.isLoading,
                onClickArrow = {
                    navController.navigate(
                        Destinations.BuildingDetailsScreenRoute(
                            id = building.id,
                            name = building.name
                        )
                    )
                },
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}