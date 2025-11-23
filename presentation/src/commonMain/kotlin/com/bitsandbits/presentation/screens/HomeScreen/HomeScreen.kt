package com.bitsandbits.presentation.screens.HomeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.Image
import coil3.compose.AsyncImage
import com.bitsandbits.designsystem.theme.theme.IndoTheme
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.component.BasicTextInputField
import com.bitsandbits.presentation.component.BuildingCard
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.component.LocationCard
import com.bitsandbits.presentation.navigation.Destinations
import com.bitsandbits.presentation.navigation.LocalNavController
import com.preat.peekaboo.image.picker.toImageBitmap
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_clear
import indo.presentation.generated.resources.ic_search
import indo.presentation.generated.resources.image_place_holder
import indo.presentation.generated.resources.library
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
    LazyColumn(contentPadding = PaddingValues(bottom = 112.dp, top = 12.dp), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        item {
            BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/qYMsBpxM/floo"))
        }
        item {
            BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/qYMsBpxM/floor-0.png"))
        }
        item {
            BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/dJ5bKQJM/floor-1.png"))
        }
        item {
            BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/LhzNZqbr/floor-2.png"))
        }
        item {
            BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity Building", imageUrl = "https://i.ibb.co/qLK13KMB/floor-3.png"))
        }
    }
}