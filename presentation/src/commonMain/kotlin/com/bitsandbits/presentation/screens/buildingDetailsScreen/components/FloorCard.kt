package com.bitsandbits.presentation.screens.buildingDetailsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.uiState.FloorUiState
import com.bitsandbits.presentation.common.utils.toFloorName
import com.bitsandbits.presentation.component.ImageViewer
import com.bitsandbits.presentation.component.IndoImageSource
import com.bitsandbits.presentation.component.LabelChip

@Composable
fun FloorCard(
    floorUiState: FloorUiState?,
    modifier: Modifier = Modifier,
    onClickImage: () -> Unit = {}
) {
    if (floorUiState != null) {
        Column(
            modifier = modifier
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(24.dp),
                    clip = false
                )
                .clip(RoundedCornerShape(24.dp))
                .background(Theme.color.onSecondaryContainer)
                .fillMaxWidth()
                .height(220.dp)
        ) {
            ImageViewer(
                image = IndoImageSource.Url(floorUiState.imageUrl ?: ""),
                isLoading = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )


            Spacer(Modifier.weight(1f))
            Text(
                text = floorUiState.floorNumber.toFloorName(),
                color = Theme.color.secondary,
                style = Theme.textStyle.labelMedium,
                modifier = Modifier.padding(start = 8.dp),
            )
            Spacer(Modifier.weight(1f))
        }
    }

}