package com.bitsandbits.presentation.screens.buildingDetailsScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme

@Composable
fun GradientFilter(modifier: Modifier = Modifier.fillMaxWidth().height(40.dp), color: Color = Color.Gray) {
    Box(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    color
                )
            )
        )
    )
}