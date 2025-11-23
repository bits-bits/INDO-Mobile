package com.bitsandbits.presentation

import androidx.compose.runtime.Composable
import com.bitsandbits.presentation.component.BuildingCard
import com.bitsandbits.presentation.screens.HomeScreen.HomeUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PreviewBuildingCard(){
    BuildingCard(HomeUiState.BuildingUiState(id = "", name = "Electricity", imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"))
}