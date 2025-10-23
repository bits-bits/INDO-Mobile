package com.bitsandbits.presentation.screens.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.IndoTheme
import com.bitsandbits.designsystem.theme.theme.Theme

@Composable
fun HomeScreen(){
    IndoTheme {
        Column(modifier = Modifier.size(100.dp).background(Color.Red)) {
            Text(text = "hello Indo", modifier = Modifier.background(Theme.color.primary), style = Theme.textStyle.labelLarge)
        }
    }
}