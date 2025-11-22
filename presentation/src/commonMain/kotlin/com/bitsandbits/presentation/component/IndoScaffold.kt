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
import com.bitsandbits.presentation.screens.HomeScreen.HomeScreen
import com.bitsandbits.presentation.screens.HomeScreen.HomeViewModel
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
fun IndoScaffold() {
    IndoTheme {
        Box(modifier = Modifier.fillMaxSize().background(Theme.color.onSecondary)) {
            var index by remember { mutableStateOf(0) }
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
                    .height(80.dp)
                    .background(Theme.color.onSecondaryContainer),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.home_icon),
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(if (index == 0) Theme.color.onPrimary else Color.Transparent),
                        colorFilter = ColorFilter.tint(Color(0xFF6B7280)),
                        contentDescription = null
                    )
                    Text(
                        "Home",
                        color = Color.White,
                        modifier = Modifier
                            .clickable { index = 0 }
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.map),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (index == 1) Theme.color.onPrimary else Color.Transparent),
                    )
                    Text(
                        "map",
                        color = Color.White,
                        modifier = Modifier
                            .clickable { index = 1 }
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.library),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (index == 2) Theme.color.onPrimary else Color.Transparent),
                    )

                    Text(
                        "library",
                        color = Color.White,
                        modifier = Modifier
                            .clickable { index = 2 }
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(Res.drawable.profile),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(if (index == 3) Theme.color.onPrimary else Color.Transparent),
                    )

                    Text(
                        "profile",
                        color = Color.White,
                        modifier = Modifier
                            .clickable { index = 3 }
                    )
                }
            }
        }
    }
}