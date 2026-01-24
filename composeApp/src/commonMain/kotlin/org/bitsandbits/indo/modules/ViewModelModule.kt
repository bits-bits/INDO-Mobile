package org.bitsandbits.indo.modules

import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsViewModel
import com.bitsandbits.presentation.screens.homeScreen.HomeViewModel
import com.bitsandbits.presentation.screens.mapScreen.MapViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get())}
    viewModel { BuildingDetailsViewModel(buildingsRepository = get(), floorRepository = get(),args = get()) }
    viewModel { MapViewModel(get(), get(),get()) }
}