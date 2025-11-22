package com.bitsandbits.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.bitsandbits.entity.Location
import com.bitsandbits.presentation.component.LocationCard

@PreviewLightDark
@Composable
fun PreviewLocationCard2() {
    LocationCard(
        location = Location(
            id = "212",
            name = "C201",
            aliasName = "h301",
            floorNumber = 2,
            buildingName = "ssp"
        )
    )
}