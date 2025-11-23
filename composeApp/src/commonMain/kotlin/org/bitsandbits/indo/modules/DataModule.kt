package org.bitsandbits.indo.modules
import com.bitsandbits.data.remote.api.BuildingApiService
import com.bitsandbits.data.remote.api.LocationApiService
import com.bitsandbits.data.repository.BuildingsRepositoryImpl
import com.bitsandbits.data.repository.LocationRepositoryImpl
import com.bitsandbits.repository.BuildingsRepository
import com.bitsandbits.repository.LocationRepository
import org.koin.dsl.module

val dataModule = module {
    single<LocationApiService> { LocationApiService() }
    single<BuildingApiService> { BuildingApiService() }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<BuildingsRepository> { BuildingsRepositoryImpl(get()) }
}