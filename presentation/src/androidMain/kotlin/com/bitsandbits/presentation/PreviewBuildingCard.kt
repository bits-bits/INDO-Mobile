package com.bitsandbits.presentation

import androidx.compose.runtime.Composable
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.screens.homeScreen.components.BuildingCard
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewBuildingCard(){
    BuildingCard(
        BuildingUiState(
            id = "",
            name = "Electricity",
            imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
        )
    )
}