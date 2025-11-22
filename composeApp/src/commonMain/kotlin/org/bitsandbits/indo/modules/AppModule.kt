package org.bitsandbits.indo.modules

import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    includes(
        dataModule,
        domainModule,
        presentationModule
    )
}