package com.bitsandbits.presentation.screens.buildingDetailsScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.shimmerEffect
import com.bitsandbits.presentation.common.utils.EffectHandler
import com.bitsandbits.presentation.component.FloorsPager
import com.bitsandbits.presentation.component.ImageViewer
import com.bitsandbits.presentation.component.IndoImageSource
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.component.LocationCard
import com.bitsandbits.presentation.navigation.LocalNavController
import com.bitsandbits.presentation.screens.buildingDetailsScreen.components.GradientFilter
import indo.presentation.generated.resources.Floors
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_arrow_left
import indo.presentation.generated.resources.ic_back_arrow
import indo.presentation.generated.resources.locations
import kotlinx.coroutines.flow.SharedFlow
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun BuildingDetailsScreen(viewModel: BuildingDetailsViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect
    EffectsHandler(effect = effect)
    BuildingDetailsContent(
        state = state,
        interactions = viewModel as BuildingDetailsInteractionListener
    )
}

@Composable
fun BuildingDetailsContent(
    state: BuildingDetailsUiState,
    interactions: BuildingDetailsInteractionListener
) {
    if (state.isLoading.not()){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.onSecondary),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {

            item{
                Column(
                    modifier = Modifier
                        .background(Theme.color.onSecondary)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                    ) {
                        ImageViewer(
                            IndoImageSource.Url(value = state.building.imageUrl ?: ""),
                            modifier = Modifier.fillMaxSize()
                        )
                        GradientFilter(
                            modifier = Modifier.fillMaxWidth().height(40.dp).align(Alignment.BottomCenter),
                            color = Theme.color.onSecondary
                        )
                        Box(modifier = Modifier.padding(top = 32.dp, start = 12.dp).size(42.dp).clip(CircleShape).background(Color.White).clickable(onClick = interactions::onClickBack), contentAlignment = Alignment.Center){
                            Image(painter = painterResource(Res.drawable.ic_arrow_left), contentDescription = null)
                        }
                    }
                    Text(
                        text = state.building.name,
                        color = Color.Black,
                        style = Theme.textStyle.labelLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    if (state.building.description != null){
                        Text(
                            text = state.building.description,
                            color = Theme.color.secondary,
                            style = Theme.textStyle.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        )
                    }

                    Text(
                        text = stringResource(Res.string.Floors),
                        color = Color.Black,
                        style = Theme.textStyle.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                    )

                    FloorsPager(floors = state.floors, modifier = Modifier.padding(bottom = 20.dp), onClickFloor = interactions::onClickFloor)

                    Text(
                        text = stringResource(Res.string.locations),
                        color = Color.Black,
                        style = Theme.textStyle.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
            println("TAG BOB, is floor loading: ${state.isFloorLocationsLoading}")
            val locations = state.selectedFloorDetailsUiState?.locations

            if (state.isFloorLocationsLoading){
                items(5){
                    Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(40.dp).clip(RoundedCornerShape(12.dp)).shimmerEffect())
                }
            }else{
                items(locations?.size ?: 0) { locationIndex ->
                    LocationCard(location = locations?.get(locationIndex), modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }

    }else{
        LoadingComponent()
    }
}

@Composable
private fun EffectsHandler(effect: SharedFlow<BuildingDetailsEffect>) {
    val navController = LocalNavController.current
    EffectHandler(effect, key1 = navController.currentBackStackEntry) { effect ->
        when (effect) {
            is BuildingDetailsEffect.NavigateToSeeAllLocationsScreen -> TODO()
            is BuildingDetailsEffect.NavigateTooSeeAllFloorsScreen -> TODO()
            BuildingDetailsEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }
}