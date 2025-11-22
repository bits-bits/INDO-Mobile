package com.bitsandbits.presentation.screens.libraryScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bitsandbits.designsystem.theme.theme.Theme

@Composable
fun LibraryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "Library Screen", style = Theme.textStyle.labelMedium)
    }
}