package com.bitsandbits.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.uiState.FloorUiState
import com.bitsandbits.presentation.screens.buildingDetailsScreen.components.FloorCard

@Composable
fun FloorsPager(
    modifier: Modifier = Modifier,
    floors: List<FloorUiState>
) {
    val pagerState = rememberPagerState(pageCount = { floors.size })
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            modifier = modifier.padding(bottom = 12.dp),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 12.dp
        ) { page ->
            val floor = floors[page]
            FloorCard(floorUiState = floor)
        }
        IndoPagerIndicator(pageCount = floors.size, currentPage = pagerState.currentPage, isRtl = true)
    }

}

@Composable
fun IndoPagerIndicator(
    pageCount: Int,
    currentPage: Int,
    isRtl: Boolean,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            val indicatorRange =
                if (isRtl) (pageCount - 1) downTo 0 else 0 until pageCount
            repeat(pageCount) { index ->
                val isSelected = currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (isSelected) 15.dp else 5.dp, 5.dp)
                        .clip(if (isSelected) RoundedCornerShape(50) else CircleShape)
                        .background(
                            if (isSelected)
                                Color.Blue.copy(alpha = 0.5f)
                            else
                                Color.Red.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}