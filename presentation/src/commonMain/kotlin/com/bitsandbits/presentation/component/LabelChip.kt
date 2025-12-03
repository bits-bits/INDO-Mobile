package com.bitsandbits.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.common.shimmerEffect

@Composable
fun LabelChip(
    modifier: Modifier = Modifier.height(40.dp),
    text: String,
    outlineColor: Color = Theme.color.secondary,
    surfaceColor: Color = Color.White,
    textColor: Color = Theme.color.secondary,
    textStyle: TextStyle = Theme.textStyle.labelMedium,
    isLoading: Boolean = false
) {
    if (isLoading){
        Box(modifier = modifier
            .width(120.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(width = 1.dp, color = outlineColor, shape = RoundedCornerShape(24.dp))
            .shimmerEffect())

    }else{
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(24.dp))
                .border(width = 1.dp, color = outlineColor, shape = RoundedCornerShape(24.dp))
                .background(surfaceColor)
                .padding(horizontal = 8.dp, vertical = 8.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = text, style = textStyle, color = textColor)
        }
    }
}