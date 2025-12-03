package com.bitsandbits.presentation.screens.homeScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.uiState.BuildingUiState
import com.bitsandbits.presentation.component.ImageViewer
import com.bitsandbits.presentation.component.IndoImageSource
import com.bitsandbits.presentation.component.LabelChip
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_circle_arrow_right
import org.jetbrains.compose.resources.painterResource

@Composable
fun BuildingCard(
    building: BuildingUiState?,
    modifier: Modifier = Modifier,
    onClickImage: () -> Unit = {},
    onClickArrow: () -> Unit = {},
    isLoading: Boolean = false,
) {

    println("TAG joee, building card, is dataLoading: $isLoading")
    Column(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(24.dp),
                clip = false
            )
            .clip(RoundedCornerShape(24.dp))
            .background(Theme.color.onSecondaryContainer)
            .fillMaxWidth()
            .height(320.dp)

    ) {
        ImageViewer(
            image = IndoImageSource.Url(
                building?.imageUrl ?: ""
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .clickable(onClick = { onClickImage() }),
            isLoading = isLoading
        )


        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier.padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LabelChip(
                text = building?.name ?: "",
                textStyle = Theme.textStyle.bodyLarge,
                surfaceColor = Theme.color.onSecondary,
                outlineColor = Theme.color.primaryContainer,
                isLoading = isLoading
            )
            Spacer(Modifier.weight(1f))
            ImageViewer(
                image = IndoImageSource.PainterSource(painterResource(Res.drawable.ic_circle_arrow_right)),
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(24.dp)
                    .clickable(onClick = { onClickArrow() })
            )
        }
        Spacer(Modifier.weight(1f))
    }
}
