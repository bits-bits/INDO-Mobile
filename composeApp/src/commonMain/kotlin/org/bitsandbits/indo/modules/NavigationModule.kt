package org.bitsandbits.indo.modules

import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsArgs
import com.bitsandbits.presentation.screens.buildingDetailsScreen.BuildingDetailsArgsImpl
import com.bitsandbits.presentation.screens.mapScreen.MapArgs
import com.bitsandbits.presentation.screens.mapScreen.MapArgsImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val navigationModule = module {
    factoryOf(::BuildingDetailsArgsImpl) bind BuildingDetailsArgs::class
    factoryOf(::MapArgsImpl) bind MapArgs::class
}