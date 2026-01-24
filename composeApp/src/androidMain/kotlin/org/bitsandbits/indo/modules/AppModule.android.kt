package org.bitsandbits.indo.modules

import com.bitsandbits.data.repository.UserLocationRepositoryImpl
import com.bitsandbits.repository.UserLocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<UserLocationRepository> { UserLocationRepositoryImpl(androidContext()) }
}