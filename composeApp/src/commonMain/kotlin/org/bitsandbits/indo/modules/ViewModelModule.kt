package org.bitsandbits.indo.modules

import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsViewModel
import com.bitsandbits.presentation.screens.homeScreen.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { BuildingDetailsViewModel(buildingsRepository = get(), args = get()) }
}