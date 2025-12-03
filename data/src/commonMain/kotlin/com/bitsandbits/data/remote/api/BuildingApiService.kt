package com.bitsandbits.data.remote.api

import com.bitsandbits.data.remote.dto.BuildingDetailsResponse
import com.bitsandbits.data.remote.dto.BuildingResponse
import com.bitsandbits.data.remote.dto.FloorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class BuildingApiService(private val client: HttpClient = NetworkClient.client) {
    suspend fun getAllBuildings(): List<BuildingResponse> {
        return client.get("/api/v1$GET_ALL_BUILDINGS_ENDPOINT").body<List<BuildingResponse>>()
    }

    suspend fun getBuildingDetailsById(id: String): BuildingDetailsResponse {
//        return BuildingDetailsResponse(
//            floorsBasicDetailResponses = fakeFloorsBasicDetailsResponse,
//            id = "1",
//            imageUrl = "https://wallpapers.com/images/thumbnail/cute-cat-sunglasses-profile-picture-mw7qp9gjrp272zky.png",
//            name = "Building zoz"
//        )
        return client.get("/api/v1$GET_BUILDING_BY_ID_ENDPOINT/$id").body<BuildingDetailsResponse>()
    }

    private companion object {
        private const val GET_ALL_BUILDINGS_ENDPOINT = "/building/all"
        private const val GET_BUILDING_BY_ID_ENDPOINT = "/building"
    }
}

val fakeBuildingResponse = BuildingResponse(
    id = "1",
    name = "Building 1",
    imageUrl = "https://i.ibb.co/qYMsBpxM/floor-0.png"
)

val fakeBuildingResponse2 = BuildingResponse(
    id = "2",
    name = "Building 2",
    imageUrl = "https://i.ibb.co/dJ5bKQJM/floor-1.png"
)

val fakeBuildingResponse3 = BuildingResponse(
    id = "3",
    name = "Building 3",
    imageUrl = "https://i.ibb.co/qYMsBpxM/floor-0.png"
)

val fakeBuildingResponse4 = BuildingResponse(
    id = "4",
    name = "Building 4",
    imageUrl = "https://i.ibb.co/dJ5bKQJM/floor-1.png"
)

val fakeBuildings = listOf(
    fakeBuildingResponse,
    fakeBuildingResponse2,
    fakeBuildingResponse3,
    fakeBuildingResponse4
)

val fakeFloorBasicDetails = FloorResponse(
    id = "A",
    number = 1,
    imageUrl = ""
)

val fakeFloorBasicDetails2 = FloorResponse(
    id = "B",
    number = 2,
    imageUrl = ""
)

val fakeFloorBasicDetails3 = FloorResponse(
    id = "C",
    number = 3,
    imageUrl = ""
)

val fakeFloorBasicDetails4 = FloorResponse(
    id = "D",
    number = 4,
    imageUrl = ""
)

val fakeFloorBasicDetails5 = FloorResponse(
    id = "E",
    number = 5,
    imageUrl = ""
)

val fakeFloorsBasicDetailsResponse = listOf(
    fakeFloorBasicDetails,
    fakeFloorBasicDetails2,
    fakeFloorBasicDetails3,
    fakeFloorBasicDetails4,
    fakeFloorBasicDetails5,
)