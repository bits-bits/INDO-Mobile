package com.bitsandbits.presentation.screens.HomeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.bitsandbits.designsystem.theme.theme.IndoTheme
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.component.BasicTextInputField
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.component.LocationCard
import com.bitsandbits.presentation.navigation.Destinations
import com.bitsandbits.presentation.navigation.LocalNavController
import com.preat.peekaboo.image.picker.toImageBitmap
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.library
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinInject()) {
    val state by homeViewModel.state.collectAsState()
    HomeScreenContent(state, homeViewModel as HomeInteractionListener)
}

@Composable
fun HomeScreenContent(state: HomeUiState, interactionListener: HomeInteractionListener) {
    val navController = LocalNavController.current

    if (state.searchTab.isLoading) {
        LoadingComponent()
    } else {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).statusBarsPadding()
                .padding(top = 12.dp)
        ) {
            BasicTextInputField(
                value = state.searchTab.searchQuery,
                endIconPainter = painterResource(
                    Res.drawable.library
                ),
                hintText = "Search . . .",
                onValueChange = { query ->
                    interactionListener.onChangeQuery(query = query)
                },
                startIconPainter = painterResource(
                    Res.drawable.library
                ),
                onClickEndIcon = {
                    interactionListener.onClickSearch()
                }
            )
            val imageUrl =
                "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png"
            LaunchedEffect(Unit) {
                interactionListener.downloadImage(imageUrl)
            }
            val imageBitmap = state.tempImage?.toImageBitmap()
            if (imageBitmap != null) {
                println("TAG bob, image bitmap is: $imageBitmap")
                Image(
                    bitmap = imageBitmap,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                )
            }
            AnimatedVisibility(visible = state.searchTab.showNumberOfResults) {
                Text(
                    text = "Total Results Found: ${state.searchTab.locations.count()}",
                    style = Theme.textStyle.labelSmall,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(bottom = 80.dp, top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val locations = state.searchTab.locations
                items(state.searchTab.locations.count()) { locationIndex ->

                    LocationCard(location = locations[locationIndex])
                }
            }
        }
    }
}