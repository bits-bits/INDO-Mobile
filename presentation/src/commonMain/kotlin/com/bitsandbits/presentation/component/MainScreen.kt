package com.bitsandbits.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.IndoTheme
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.navigation.MainScreenDestinations
import com.bitsandbits.presentation.navigation.index
import com.bitsandbits.presentation.screens.HomeScreen.HomeScreen
import com.bitsandbits.presentation.screens.MapScreen.MapScreen
import com.bitsandbits.presentation.screens.ProfileScreen.ProfileScreen
import com.bitsandbits.presentation.screens.libraryScreen.LibraryScreen
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.home_icon
import indo.presentation.generated.resources.library
import indo.presentation.generated.resources.map
import indo.presentation.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun MainScreen(selectedTab: MainScreenDestinations = MainScreenDestinations.Home) {
    IndoTheme {
        Box(modifier = Modifier.fillMaxSize().background(Theme.color.onSecondary)) {
            var index by remember { mutableStateOf(selectedTab.index) }
            if (index == 0) {
                HomeScreen()
            }
            if (index == 1) {
                MapScreen()
            }
            if (index == 2) {
                LibraryScreen()
            }

            if (index == 3) {
                ProfileScreen()
            }

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(90.dp)
                    .background(Theme.color.onSecondaryContainer)
                    .navigationBarsPadding(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { index = 0 }) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) Theme.color.onPrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.home_icon),
                            colorFilter = if (index == 0) ColorFilter.tint(Theme.color.onSecondaryContainer) else ColorFilter.tint(Color(0xFF6B7280)),
                            modifier = Modifier.size(28.dp),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "Home",
                        color = Theme.color.primary,
                        style = Theme.textStyle.bodyMedium,
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { index = 1 }) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (index == 1) Theme.color.onPrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.map),
                            colorFilter = if (index == 1) ColorFilter.tint(Theme.color.onSecondaryContainer) else ColorFilter.tint(Color(0xFF6B7280)),
                            contentDescription = null
                        )
                    }
                    Text(
                        text = "map",
                        color = Theme.color.primary,
                        style = Theme.textStyle.bodyMedium,
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { index = 2 }) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (index == 2) Theme.color.onPrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.library),
                            colorFilter = if (index == 2) ColorFilter.tint(Theme.color.onSecondaryContainer) else ColorFilter.tint(Theme.color.secondary),
                            contentDescription = null
                        )
                    }

                    Text(
                        text = "library",
                        color = Theme.color.primary,
                        style = Theme.textStyle.bodyMedium,
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable { index = 3 }) {
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .clip(CircleShape)
                            .background(if (index == 3) Theme.color.onPrimary else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(Res.drawable.profile),
                            colorFilter = if (index == 3) ColorFilter.tint(Theme.color.onSecondaryContainer) else ColorFilter.tint(Color(0xFF6B7280)),
                            contentDescription = null
                        )
                    }

                    Text(
                        text = "profile",
                        color = Theme.color.primary,
                        style = Theme.textStyle.bodyMedium,
                    )
                }
            }
        }
    }
}