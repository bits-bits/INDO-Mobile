package com.bitsandbits.presentation.screens.mapScreen.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bitsandbits.presentation.screens.mapScreen.MapUiState
import com.bitsandbits.presentation.screens.mapScreen.PointUiState
import org.maplibre.compose.camera.CameraPosition
import org.maplibre.compose.camera.rememberCameraState
import org.maplibre.compose.expressions.dsl.const
import org.maplibre.compose.expressions.value.LineCap
import org.maplibre.compose.expressions.value.LineJoin
import org.maplibre.compose.layers.CircleLayer
import org.maplibre.compose.layers.LineLayer
import org.maplibre.compose.map.MaplibreMap
import org.maplibre.compose.sources.GeoJsonData
import org.maplibre.compose.sources.rememberGeoJsonSource
import org.maplibre.compose.style.BaseStyle
import org.maplibre.spatialk.geojson.Position

private fun createLineStringJson(coordinates: List<Position>): String {
    if (coordinates.isEmpty()) {
        return """
            {
                "type": "FeatureCollection",
                "features": []
            }
        """.trimIndent()
    }

    return """
        {
            "type": "Feature",
            "geometry": {
                "type": "LineString",
                "coordinates": [
                    ${coordinates.joinToString(",") { "[${it.longitude},${it.latitude}]" }}
                ]
            }
        }
    """.trimIndent()
}

private fun createPointJson(coordinate: Position): String {
    return """
        {
            "type": "Feature",
            "geometry": {
                "type": "Point",
                "coordinates": [${coordinate.longitude},${coordinate.latitude}]
            }
        }
    """.trimIndent()
}

@Composable
fun MapWithRoute(
    mapUiState: MapUiState,
    mapStyleUrl: String = "https://tiles.openfreemap.org/styles/liberty"
) {
    // Memoize positions to prevent unnecessary recompositions
    val routePositions = remember(mapUiState.points) {
        mapUiState.points.map { it.toPosition() }
    }

    val endPosition = remember(mapUiState.endPoint) {
        mapUiState.endPoint?.toPosition()
    }

    val currentPosition = remember(mapUiState.currentPositionPoint) {
        mapUiState.currentPositionPoint?.toPosition()
    }

    val initialPosition = remember(mapUiState.initialUserPosition) {
        mapUiState.initialUserPosition?.toPosition()
    }


    val cameraState = rememberCameraState(
        firstPosition = CameraPosition(
            target = Position(latitude = 31.207313, longitude = 29.924058),
            zoom = 15.3
        )
    )

    MaplibreMap(
        modifier = Modifier.fillMaxSize(),
        baseStyle = BaseStyle.Uri(mapStyleUrl),
        cameraState = cameraState
    ) {
        val routeSource = rememberGeoJsonSource(
            GeoJsonData.JsonString(createLineStringJson(mapUiState.points.map { it.toPosition() }))
        )

        if (routePositions.isNotEmpty()) {
            println("TAG BOB route in maps is: ${mapUiState.points}")
            LineLayer(
                id = "route-line",
                source = routeSource,
                color = const(Color.Blue),
                width = const(4.dp),
                cap = const(LineCap.Round),
                join = const(LineJoin.Round)
            )
        }


        endPosition?.let { position ->
            val endPointSource = rememberGeoJsonSource(
                GeoJsonData.JsonString(createPointJson(position))
            )

            CircleLayer(
                id = "end-point-circle",
                source = endPointSource,
                color = const(Color.Red),
                radius = const(6.dp)
            )
        }

        currentPosition?.let { position ->
            val currentPositionSource = rememberGeoJsonSource(
                GeoJsonData.JsonString(createPointJson(position))
            )

            CircleLayer(
                id = "current-point-circle",
                source = currentPositionSource,
                color = const(Color.Green),
                radius = const(6.dp)
            )
        }

        initialPosition?.let { position ->
            val initialPositionSource = rememberGeoJsonSource(
                GeoJsonData.JsonString(createPointJson(position))
            )
            println("TAG BOB route in maps is : initial position ${mapUiState.initialUserPosition}")
            CircleLayer(
                id = "initial-point-circle",
                source = initialPositionSource,
                color = const(Color.Black),
                radius = const(6.dp)
            )
        }

    }
}

val fakeRoute = listOf(
    Position(longitude = 29.924426, latitude = 31.205959),
    Position(longitude = 29.924276, latitude = 31.205897),
    Position(longitude = 29.924027, latitude = 31.205795),
    Position(longitude = 29.923958, latitude = 31.205795),
    Position(longitude = 29.923912, latitude = 31.205805),
    Position(longitude = 29.923872, latitude = 31.205837),
    Position(longitude = 29.923714, latitude = 31.206112),
    Position(longitude = 29.923598, latitude = 31.206066),
    Position(longitude = 29.923544, latitude = 31.20605),
    Position(longitude = 29.923494, latitude = 31.206066),
    Position(longitude = 29.923453, latitude = 31.20611),
    Position(longitude = 29.922925, latitude = 31.207115),
    Position(longitude = 29.922922, latitude = 31.207174),
    Position(longitude = 29.922938, latitude = 31.20722),
    Position(longitude = 29.922992, latitude = 31.207257),
    Position(longitude = 29.92317, latitude = 31.207326),
    Position(longitude = 29.922915, latitude = 31.207788),
    Position(longitude = 29.922758, latitude = 31.208078),
    Position(longitude = 29.922834, latitude = 31.208116),
    Position(longitude = 29.923436, latitude = 31.208419),
    Position(longitude = 29.9235, latitude = 31.208453),
    Position(longitude = 29.923559, latitude = 31.208495),
    Position(longitude = 29.923633, latitude = 31.208424),
    Position(longitude = 29.923683, latitude = 31.208363),
    Position(longitude = 29.923697, latitude = 31.208344),
    Position(longitude = 29.92380737476708, latitude = 31.20814354559775),
    Position(longitude = 29.92363271196666, latitude = 31.208089661061308),
    Position(longitude = 29.92371600509034, latitude = 31.20793901239395),
    Position(longitude = 29.923825411827465, latitude = 31.20780650074805),
)

val fakeRoute2 = listOf(
    Position(longitude = 30.357626, latitude = 30.50728),
    Position(longitude = 30.357651, latitude = 30.507228),
    Position(longitude = 30.357743, latitude = 30.507057),
    Position(longitude = 30.357842, latitude = 30.506891),
    Position(longitude = 30.357947, latitude = 30.506731),
    Position(longitude = 30.358059, latitude = 30.506576),
    Position(longitude = 30.358177, latitude = 30.506429),
    Position(longitude = 30.360221, latitude = 30.504015),
    Position(longitude = 30.360314, latitude = 30.503889),
    Position(longitude = 30.3604, latitude = 30.503757),
    Position(longitude = 30.36048, latitude = 30.50362),
    Position(longitude = 30.360552, latitude = 30.50348),
    Position(longitude = 30.360618, latitude = 30.503336),
    Position(longitude = 30.360722, latitude = 30.503114),
    Position(longitude = 30.360726, latitude = 30.503144),
    Position(longitude = 30.360736, latitude = 30.503172),
    Position(longitude = 30.360752, latitude = 30.503196),
    Position(longitude = 30.360773, latitude = 30.503214),
    Position(longitude = 30.360797, latitude = 30.503226),
    Position(longitude = 30.360823, latitude = 30.503231),
    Position(longitude = 30.360785, latitude = 30.503291),
    Position(longitude = 30.360668, latitude = 30.503494),
    Position(longitude = 30.360566, latitude = 30.503686),
    Position(longitude = 30.360456, latitude = 30.503871),
    Position(longitude = 30.360339, latitude = 30.50405),
    Position(longitude = 30.360214, latitude = 30.504223),
    Position(longitude = 30.360082, latitude = 30.504388),
    Position(longitude = 30.359023, latitude = 30.505625),
    Position(longitude = 30.359282, latitude = 30.505924),
    Position(longitude = 30.359749, latitude = 30.506463)
)

private fun PointUiState.toPosition(): Position {
    return Position(latitude = this.latitude, longitude = this.longitude)
}