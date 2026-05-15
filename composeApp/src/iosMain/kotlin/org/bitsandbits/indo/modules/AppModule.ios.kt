package org.bitsandbits.indo.modules

import com.bitsandbits.data.repository.TokenStorageRepo
import com.bitsandbits.data.repository.TokenStorageRepoImpl
import com.bitsandbits.data.repository.UserLocationRepositoryImpl
import com.bitsandbits.repository.UserLocationRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<UserLocationRepository> { UserLocationRepositoryImpl() }
    single<TokenStorageRepo> { TokenStorageRepoImpl() }
}