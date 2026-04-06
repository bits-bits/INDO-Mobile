package com.bitsandbits.presentation.screens.mapScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.presentation.component.LoadingComponent
import com.bitsandbits.presentation.screens.mapScreen.component.MapWithRoute
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.ic_arrow_left
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MapScreen(mapViewModel: MapViewModel = koinViewModel()) {
    val state by mapViewModel.state.collectAsState()
    MapScreenContent(state = state)
}

@Composable
private fun MapScreenContent(state: MapUiState){
    if (state.isLoading.not()){
        Box(modifier = Modifier.fillMaxSize()) {
            MapWithRoute(mapUiState = state)

            var showInfo by remember { mutableStateOf(false) }
            val showInfoWidth by animateFloatAsState(if (showInfo) 300f else 30f, tween(500))

            val showInfoModifierHeight = if(showInfo) Modifier.wrapContentHeight() else Modifier.height(100.dp)
            val showInfoPadding = if(showInfo) 8.dp else 0.dp

            Column(
                modifier = Modifier
                    .width(showInfoWidth.dp)
                    .then(showInfoModifierHeight)
                    .shadow(8.dp, RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                    .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
                    .background(color = Color.White).align(Alignment.TopEnd)
                    .clickable { showInfo = !showInfo }
                    .padding(all = showInfoPadding),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                if(showInfo){
                    InfoRow(
                        title = "Ground floor",
                        endContent = { Box(modifier = Modifier.height(5.dp).width(40.dp).clip(RoundedCornerShape(3.dp)).background(color = Color.Blue))}

                    )
                    if(state.floorNumber != 0){
                        InfoRow(
                            title = floorNumberMapper(state.floorNumber),
                            endContent = { Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                repeat(5){
                                    Box(modifier = Modifier.height(4.dp).width(9.dp).clip(RoundedCornerShape(3.dp)).background(color = Color(0xFFF97316)))
                                }
                            } }
                        )

                        InfoRow(
                            title = "Stairs",
                            endContent = { Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color = Color(0xFFF59E0B)))}
                        )
                    }


                    InfoRow(
                        title = "Destination",
                        endContent = { Box(modifier = Modifier.size(10.dp).clip(CircleShape).background(color = Color.Red))}
                    )
                }else{
                    Spacer(modifier = Modifier.weight(1f))
                    Image(painter = painterResource(Res.drawable.ic_arrow_left), colorFilter = ColorFilter.tint(Theme.color.onPrimary), contentDescription = null, modifier = Modifier.size(20.dp).align(Alignment.CenterHorizontally))
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }else{
        LoadingComponent()
    }
}

@Composable
private fun InfoRow(
    title: String,
    endContent: @Composable () -> Unit
){
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Text(title, color = Theme.color.onPrimary, style = Theme.textStyle.bodyMedium)
        endContent()
    }

}

private fun floorNumberMapper(floorNumber: Int?): String{
    return when(floorNumber){
        null -> "Upper floor"
        0 -> "Ground floor"
        1 -> "First floor"
        2 -> "Second floor"
        3 -> "Third floor"
        else -> "Floor $floorNumber"
    }
}