package org.bitsandbits.indo.modules
import com.bitsandbits.data.remote.api.BuildingApiService
import com.bitsandbits.data.remote.api.LocationApiService
import com.bitsandbits.data.repository.BuildingRepositoryImpl
import com.bitsandbits.data.repository.LocationRepositoryImpl
import com.bitsandbits.repository.BuildingRepository
import com.bitsandbits.repository.LocationRepository
import org.koin.dsl.module

val dataModule = module {
    single<LocationApiService> { LocationApiService() }
    single<BuildingApiService> { BuildingApiService() }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<BuildingRepository> { BuildingRepositoryImpl(get()) }
}