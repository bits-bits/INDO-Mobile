package org.bitsandbits.indo.modules

import android.content.SharedPreferences
import com.bitsandbits.data.repository.RoutersRepositoryImpl
import com.bitsandbits.data.repository.TokenStorageRepo
import com.bitsandbits.data.repository.TokenStorageRepoRepoImpl
import com.bitsandbits.data.repository.UserLocationRepositoryImpl
import com.bitsandbits.data.storage.SecurePrefsFactory
import com.bitsandbits.repository.RoutersRepository
import com.bitsandbits.repository.UserLocationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<UserLocationRepository> { UserLocationRepositoryImpl(androidContext()) }
    single<SharedPreferences> { SecurePrefsFactory.create(androidContext()) }
    single<TokenStorageRepo> { TokenStorageRepoRepoImpl(get()) }
    single<RoutersRepository> { RoutersRepositoryImpl(androidContext()) }
}