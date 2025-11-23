package com.bitsandbits.presentation.screens.buildingDetailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.utils.EffectHandler
import com.bitsandbits.presentation.component.ImageViewer
import com.bitsandbits.presentation.component.IndoImageSource
import com.bitsandbits.presentation.navigation.LocalNavController
import com.bitsandbits.presentation.screens.buildingDetailsScreen.components.GradientFilter
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.image_place_holder
import kotlinx.coroutines.flow.SharedFlow
import org.jetbrains.compose.resources.painterResource
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
    Column(modifier = Modifier.fillMaxSize().background(Color.Cyan)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            ImageViewer(
                IndoImageSource.PainterSource(value = painterResource(Res.drawable.image_place_holder)),
                modifier = Modifier.fillMaxSize()
            )
            GradientFilter(
                modifier = Modifier.fillMaxWidth().height(40.dp).align(Alignment.BottomCenter),
                color = Color.Cyan
            )
        }
        Text(text = state.building.name, color = Color.Black, style = Theme.textStyle.labelLarge, modifier = Modifier.padding(horizontal = 16.dp))
        if (state.building.description != null)
         Text(text = state.building.description, color = Theme.color.secondary, style = Theme.textStyle.bodyMedium, modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))

    }
}

@Composable
private fun EffectsHandler(effect: SharedFlow<BuildingDetailsEffect>) {
    val navController = LocalNavController.current
    EffectHandler(effect, key1 = navController.currentBackStackEntry) { effect ->
        when (effect) {
            is BuildingDetailsEffect.NavigateToSeeAllLocationsScreen -> TODO()
            is BuildingDetailsEffect.NavigateTooSeeAllFloorsScreen -> TODO()
        }
    }
}