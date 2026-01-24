package com.bitsandbits.presentation.screens.mapScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.bitsandbits.presentation.screens.mapScreen.component.MapWithRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MapScreen(mapViewModel: MapViewModel = koinViewModel()) {
    val state by mapViewModel.state.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        MapWithRoute(mapUiState = state)
//        Text(text = "${state.currentPositionPoint}", modifier = Modifier.background(Color.White))
    }
}