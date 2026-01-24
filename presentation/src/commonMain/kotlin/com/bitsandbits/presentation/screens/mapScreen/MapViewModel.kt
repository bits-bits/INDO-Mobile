package com.bitsandbits.presentation.screens.mapScreen

import androidx.lifecycle.viewModelScope
import com.bitsandbits.entity.Point
import com.bitsandbits.presentation.Base.BaseViewModel
import com.bitsandbits.presentation.screens.mapScreen.utils.mapper.toCoordinateType
import com.bitsandbits.presentation.screens.mapScreen.utils.mapper.toPointUiState
import com.bitsandbits.repository.LocationRepository
import com.bitsandbits.repository.UserLocationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MapViewModel(
    private val userLocationRepository: UserLocationRepository,
    private val locationRepository: LocationRepository,
    private val args: MapArgs,
) : BaseViewModel<MapUiState, Nothing>(MapUiState()) {
    init {
        getDestination()
        getInitialUserLocation()
        getCurrentUserLocation()
    }

    fun getDestination(){
        if (args.latitude != null && args.longitude != null){
            updateState {
                it.copy(
                    endPoint = PointUiState(
                        latitude = args.latitude!!,
                        longitude = args.longitude!!,
//                    latitude = 31.22357,
//                    longitude = 29.953427,
                        type = PointType.CHECK_POINT
                    )
                )
            }
        }
        if (args.locationId != null){
            updateState { it.copy(destinationLocationId = args.locationId) }
        }
        println("TAG BOB LOL DESTINATION LOCATION ID IS: ${state.value.destinationLocationId}")
    }

    fun getCurrentUserLocation() {
        println("TAG VM current location is 1111")
        tryToCollect(
            collect = { userLocationRepository.getUserLocation() },
            onCollect = ::onGetUserLocationCollected,
            onError = { println("TAG VM current location is errrro: ${it.message}") },
            dispatcher = Dispatchers.Main
        )
    }

    fun getInitialUserLocation() {
        viewModelScope.launch {
            val location = userLocationRepository.getUserLocation().filterNotNull().first()
            if (location != null) {
                updateState {
                    it.copy(
                        initialUserPosition = PointUiState(
                            latitude = location.first,
                            longitude = location.second,
                            type = PointType.CHECK_POINT
                        )
                    )
                }
                getRouteToLocation()
            }
        }
    }

    private fun onGetUserLocationCollected(location: Pair<Double, Double>?) {
        println("TAG VM current location is: $location")
        if (location != null) {
            updateState {
                it.copy(
                    currentPositionPoint = PointUiState(
                        latitude = location.first,
                        longitude = location.second,
                        type = PointType.CHECK_POINT
                    )
                )
            }
        }
    }

    private fun getRouteToLocation() {
        println("TAG BOB, JOE getRouteToLocation start = ${state.value.initialUserPosition}, end point is ${state.value.endPoint}, id of location is ${state.value.destinationLocationId}")
//        if (state.value.initialUserPosition == null || state.value.endPoint == null) return
        if (state.value.destinationLocationId == null) return
        tryToExecute(
            function = {
                locationRepository.getRouteToLocation(
                    start = getStartDestination(),
                    locationId = state.value.destinationLocationId!!
                )
//                locationRepository.getRouteBetweenTwoPoints(
//                    start = getStartDestination(),
//                    end = getEndDestination()
//                )
            },
            onSuccess = { route ->
                println("TAG VM current location get route from domain is: $route")
                updateState { it.copy(points = route.map { point -> point.toPointUiState() }) }
                println("TAG VM current location get route from domain  after map: ${state.value.points}")

            },
            onError = { println("TAG VM current location get route is errrro: ${it.message}") }
        )
    }

    private fun getStartDestination(): Point {
        if (state.value.initialUserPosition == null) throw Exception("Current position is null")
        return Point(
            latitude = state.value.initialUserPosition!!.latitude,
            longitude = state.value.initialUserPosition!!.longitude,
            type = state.value.initialUserPosition!!.type.toCoordinateType(),
        )
    }

    private fun getEndDestination(): Point {
        if (state.value.endPoint == null) throw Exception("End point is null")
        return Point(
            latitude = state.value.endPoint!!.latitude,
            longitude = state.value.endPoint!!.longitude,
            type = state.value.endPoint!!.type.toCoordinateType(),
        )
    }
}

val fakePoints = listOf(
    PointUiState(latitude = 31.205959, longitude = 29.924426, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.205897, longitude = 29.924276, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.205795, longitude = 29.924027, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.205795, longitude = 29.923958, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.205805, longitude = 29.923912, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.205837, longitude = 29.923872, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.206112, longitude = 29.923714, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.206066, longitude = 29.923598, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.206050, longitude = 29.923544, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.206066, longitude = 29.923494, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.206110, longitude = 29.923453, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207115, longitude = 29.922925, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207174, longitude = 29.922922, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207220, longitude = 29.922938, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207257, longitude = 29.922992, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207326, longitude = 29.923170, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.207788, longitude = 29.922915, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208078, longitude = 29.922758, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208116, longitude = 29.922834, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208419, longitude = 29.923436, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208453, longitude = 29.923500, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208495, longitude = 29.923559, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208424, longitude = 29.923633, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208363, longitude = 29.923683, type = PointType.CHECK_POINT),
    PointUiState(latitude = 31.208344, longitude = 29.923697, type = PointType.CHECK_POINT),

    PointUiState(
        latitude = 31.20814354559775,
        longitude = 29.92380737476708,
        type = PointType.CHECK_POINT
    ),
    PointUiState(
        latitude = 31.208089661061308,
        longitude = 29.92363271196666,
        type = PointType.CHECK_POINT
    ),
    PointUiState(
        latitude = 31.20793901239395,
        longitude = 29.92371600509034,
        type = PointType.CHECK_POINT
    ),
    PointUiState(
        latitude = 31.20780650074805,
        longitude = 29.923825411827465,
        type = PointType.CHECK_POINT
    ),
)