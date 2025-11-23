package com.bitsandbits.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import indo.presentation.generated.resources.Res
import indo.presentation.generated.resources.image_place_holder
import org.jetbrains.compose.resources.painterResource


@Composable
fun ImageViewer(
    image: IndoImageSource?,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onLoading: @Composable () -> Unit = {},
    onError: @Composable () -> Unit = { Image(painterResource(Res.drawable.image_place_holder), contentDescription = null, modifier = Modifier.fillMaxSize())},
) {
    var isImageLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    when (image) {
        is IndoImageSource.Url -> {
            Box(modifier = modifier.fillMaxSize()) {
                AsyncImage(
                    model = image.value,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize(),
                    onLoading = {
                        isImageLoading = true
                        isError = false
                    },
                    onError = {
                        isImageLoading = false
                        isError = true
                    },
                )
                when {
                    isImageLoading || isLoading -> onLoading()
                    isError -> {
                        println("TAG HOB, ON ERROR")
                        onError()
                    }
                }
            }
        }

        is IndoImageSource.Bytes -> {
            Image(
                bitmap = image.value.decodeToImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize(),
            )
        }

        is IndoImageSource.PainterSource -> {
            Image(
                painter = if (isLoading) painterResource(Res.drawable.image_place_holder) else image.value,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.FillBounds,
            )
        }

        null -> {
            Image(
                painter = painterResource(Res.drawable.image_place_holder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
fun ZoomableImage(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scale = remember { mutableStateOf(1f) }
    val offsetX = remember { mutableStateOf(0f) }
    val offsetY = remember { mutableStateOf(0f) }

    val state = rememberTransformableState { zoomChange, panChange, _ ->
        scale.value *= zoomChange
        offsetX.value += panChange.x
        offsetY.value += panChange.y
    }

    Box(
        modifier = modifier
            .transformable(state)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value,
                translationX = offsetX.value,
                translationY = offsetY.value
            )
    ) {
        content()
    }
}

sealed interface IndoImageSource {
    data class Url(val value: String) : IndoImageSource
    data class Bytes(val value: ByteArray) : IndoImageSource
    data class PainterSource(val value: Painter) : IndoImageSource
}