package com.bitsandbits.presentation.screens.buildingDetailsScreen

import app.cash.turbine.test
import com.bitsandbits.entity.*
import com.bitsandbits.repository.BuildingsRepository
import com.bitsandbits.repository.FloorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class BuildingDetailsViewModelTest {

    private lateinit var viewModel: BuildingDetailsViewModel
    private val buildingsRepository = FakeBuildingsRepository()
    private val floorRepository = FakeFloorRepository()
    private val args = FakeBuildingDetailsArgs()

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should load building details`() = runTest {
        // Given
        val buildingDetails = BuildingDetails(
            id = "1",
            name = "Building 1",
            description = "Description 1",
            imageUrl = "url1",
            longitude = 10.0,
            latitude = 20.0,
            floors = listOf(Floor("f1", 1, "furl1"))
        )
        buildingsRepository.buildingDetails = buildingDetails

        // When
        viewModel = BuildingDetailsViewModel(buildingsRepository, floorRepository, args)

        // Then
        viewModel.state.test {
            var state = awaitItem()
            while (state.building.name != "Building 1") {
                state = awaitItem()
            }
            assertEquals("Building 1", state.building.name)
            assertEquals(1, state.floors.size)
            assertEquals("f1", state.floors[0].floorId)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onClickBack should emit NavigateBack effect`() = runTest {
        // Given
        buildingsRepository.buildingDetails = BuildingDetails("1", "Building 1", null, null, null, null, emptyList())
        viewModel = BuildingDetailsViewModel(buildingsRepository, floorRepository, args)

        // When/Then
        viewModel.effect.test {
            viewModel.onClickBack()
            assertEquals(BuildingDetailsEffect.NavigateBack, awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onClickFloor should load floor details`() = runTest {
        // Given
        val floors = listOf(Floor("f1", 1, "furl1"))
        buildingsRepository.buildingDetails = BuildingDetails("1", "Building 1", null, null, null, null, floors)
        
        val floorDetails = FloorDetails(
            id = "f1",
            number = 1,
            imageUrl = "furl1",
            locations = listOf(
                Location("l1", "Loc 1", null, 1, "Building 1", "furl1", 0.0, 0.0)
            )
        )
        floorRepository.floorDetails = floorDetails
        
        viewModel = BuildingDetailsViewModel(buildingsRepository, floorRepository, args)

        // When/Then
        viewModel.state.test {
            var state = awaitItem()
            while (state.building.name != "Building 1") {
                state = awaitItem()
            }
            
            viewModel.onClickFloor("f1")
            
            while (state.selectedFloorDetailsUiState?.floorId != "f1") {
                state = awaitItem()
            }
            
            assertEquals("f1", state.selectedFloorDetailsUiState?.floorId)
            assertEquals(1, state.selectedFloorDetailsUiState?.locations?.size)
            assertEquals("Loc 1", state.selectedFloorDetailsUiState?.locations?.get(0)?.name)
            cancelAndIgnoreRemainingEvents()
        }
    }
}

class FakeBuildingsRepository : BuildingsRepository {
    var buildingDetails: BuildingDetails? = null
    override suspend fun getAllBuildings(): List<Building> = emptyList()
    override suspend fun getBuildingDetailsById(id: String): BuildingDetails {
        return buildingDetails ?: throw Exception("Not found")
    }
}

class FakeFloorRepository : FloorRepository {
    var floorDetails: FloorDetails? = null
    override suspend fun getFloorDetailsById(floorId: String): FloorDetails {
        return floorDetails ?: throw Exception("Not found")
    }
}

class FakeBuildingDetailsArgs : BuildingDetailsArgs {
    override val buildingId: String = "1"
    override val buildingName: String = "Building 1"
}
