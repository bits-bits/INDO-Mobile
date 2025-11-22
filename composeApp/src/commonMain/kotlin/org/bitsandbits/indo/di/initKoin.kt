package org.bitsandbits.indo.di

import org.bitsandbits.indo.modules.platformModule
import org.bitsandbits.indo.modules.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule, platformModule)
    }
}