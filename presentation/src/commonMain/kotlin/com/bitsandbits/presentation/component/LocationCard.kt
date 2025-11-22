package com.bitsandbits.presentation.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bitsandbits.designsystem.theme.theme.Theme
import com.bitsandbits.entity.Location
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.drop_down_arrow
import indo.presentation.generated.resources.image_place_holder
import org.jetbrains.compose.resources.painterResource

@Composable
fun LocationCard(modifier: Modifier = Modifier, location: Location) {

    var cardHeight by remember { mutableStateOf(CardConstants.MIN_CARD_HEIGHT) }
    val animatedHeight by animateDpAsState(cardHeight.dp, animationSpec = tween(300))


    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = {
                cardHeight =
                    if (cardHeight == CardConstants.MIN_CARD_HEIGHT) CardConstants.MAX_CARD_HEIGHT else CardConstants.MIN_CARD_HEIGHT
            })
            .background(Theme.color.secondaryContainer)
            .padding(horizontal = 8.dp),
    ) {
        Row() {
            Text(
                text = location.name,
                style = Theme.textStyle.bodyMedium,
                modifier = Modifier.padding(top = 10.dp)
            )

            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(Res.drawable.drop_down_arrow),
                contentDescription = null,
                modifier = Modifier.size(24.dp).padding(top = 12.dp).then(
                    if (cardHeight == CardConstants.MAX_CARD_HEIGHT) Modifier.rotate(180f) else Modifier
                )
            )
        }

        AnimatedVisibility(cardHeight == CardConstants.MAX_CARD_HEIGHT) {
            if (location.floorImageUrl == null) {
                Image(
                    painter = painterResource(Res.drawable.image_place_holder),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp)
                        .padding(horizontal = 8.dp).height(200.dp).clip(
                            RoundedCornerShape(20.dp)
                        ), contentScale = ContentScale.FillWidth
                )
            } else {
                // TODO add image url
            }
        }

        AnimatedVisibility(cardHeight == CardConstants.MAX_CARD_HEIGHT) {
            Column(Modifier.padding(top = 20.dp).fillMaxHeight()) {
                Spacer(Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.height(30.dp)
                            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Building", style = Theme.textStyle.bodyLarge)
                    }
                    Text(text = " ${location.buildingName}", style = Theme.textStyle.bodyLarge)
                }
                Spacer(Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.height(30.dp)
                            .border(2.dp, Color.DarkGray, RoundedCornerShape(20.dp))
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Floor No.", style = Theme.textStyle.bodyLarge)
                    }
                    Text(text = " ${location.floorNumber}", style = Theme.textStyle.bodyLarge)
                }

                Spacer(Modifier.weight(1f))
                Row(Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.DarkGray)
                            .clickable(onClick = {})
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Add To Favourites",
                            style = Theme.textStyle.bodyMedium,
                            color = Theme.color.secondaryContainer
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.DarkGray)
                            .clickable(onClick = {})
                            .padding(horizontal = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Find Route",
                            style = Theme.textStyle.bodyMedium,
                            color = Theme.color.secondaryContainer
                        )
                    }
                }
            }
        }
    }

}


object CardConstants {
    const val MAX_CARD_HEIGHT = 400
    const val MIN_CARD_HEIGHT = 40
}