package org.bitsandbits.indo.modules

import com.bitsandbits.presentation.screens.HomeScreen.HomeViewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { HomeViewModel(get()) }

}