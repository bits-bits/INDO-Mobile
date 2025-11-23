package com.bitsandbits.presentation.screens.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.component.BasicTextInputField
import com.bitsandbits.presentation.screens.homeScreen.components.BuildingCard
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.component.LocationCard
import com.bitsandbits.presentation.navigation.Destinations
import com.bitsandbits.presentation.navigation.LocalNavController
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_clear
import indo.presentation.generated.resources.ic_search
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
    val state by homeViewModel.state.collectAsState()
    HomeScreenContent(state, homeViewModel as HomeInteractionListener)
}

@Composable
fun HomeScreenContent(state: HomeUiState, interactionListener: HomeInteractionListener) {
    val navController = LocalNavController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .statusBarsPadding()
            .padding(top = 12.dp)
    ) {
        BasicTextInputField(
            value = state.searchTab.searchQuery,
            endIconPainter = if (state.searchTab.searchQuery.isNotBlank()) painterResource(Res.drawable.ic_clear) else null,
            hintText = "Search . . .",
            onValueChange = { query -> interactionListener.onChangeQuery(query = query) },
            startIconPainter = painterResource(Res.drawable.ic_search),
            onClickEndIcon = { interactionListener.onClickSearch() },
            modifier = Modifier.padding(bottom = 12.dp)
        )
        AnimatedVisibility(state.showSearchLayout) {
            SearchLayout(state = state.searchTab)
        }
        AnimatedVisibility(state.showBuildingsLayout) {
            BuildingDetailsLayout(state = state.buildingsTab)
        }
    }
}

@Composable
private fun SearchLayout(state: HomeUiState.SearchTab) {
    if (state.isLoading) {
        LoadingComponent()
    } else {
        AnimatedVisibility(visible = state.showNumberOfResults) {
            Text(
                text = "Total Results Found: ${state.locations.count()}",
                style = Theme.textStyle.labelSmall,
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp, top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val locations = state.locations
            items(state.locations.count()) { locationIndex ->

                LocationCard(location = locations[locationIndex])
            }
        }
    }
}

@Composable
private fun BuildingDetailsLayout(state: HomeUiState.BuildingsTab) {
    val nc = LocalNavController.current
    LazyColumn(contentPadding = PaddingValues(bottom = 112.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            BuildingCard(
                BuildingUiState(
                    id = "",
                    name = "Electricity Building",
                    imageUrl = "https://i.ibb.co/qYMsBpxM/floo"
                ),
                onClickArrow = { nc.navigate(Destinations.BuildingDetailsScreenRoute(id = "1234", name = "Electricity Building")) }
            )
        }
        item {
            BuildingCard(BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/qYMsBpxM/floor-0.png"))
        }
        item {
            BuildingCard(BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/dJ5bKQJM/floor-1.png"))
        }
        item {
            BuildingCard(BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/LhzNZqbr/floor-2.png"))
        }
        item {
            BuildingCard(building = BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/qLK13KMB/floor-3.png"))
        }
    }
}