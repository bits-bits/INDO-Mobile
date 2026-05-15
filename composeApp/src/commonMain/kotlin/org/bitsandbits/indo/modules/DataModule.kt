package org.bitsandbits.indo.modules
import com.bitsandbits.data.remote.api.BuildingApiService
import com.bitsandbits.data.remote.api.FloorApiService
import com.bitsandbits.data.remote.api.LocationApiService
import com.bitsandbits.data.repository.BuildingsRepositoryImpl
import com.bitsandbits.data.repository.FloorRepositoryImpl
import com.bitsandbits.data.repository.LocationRepositoryImpl
import com.bitsandbits.data.repository.TokenStorageRepo
import com.bitsandbits.data.repository.UserRepositoryImpl
import com.bitsandbits.repository.BuildingsRepository
import com.bitsandbits.repository.FloorRepository
import com.bitsandbits.repository.LocationRepository
import com.bitsandbits.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<LocationApiService> { LocationApiService() }
    single<BuildingApiService> { BuildingApiService() }
    single<FloorApiService> { FloorApiService() }

    single<LocationRepository> { LocationRepositoryImpl(get()) }
    single<BuildingsRepository> { BuildingsRepositoryImpl(get()) }
    single<FloorRepository> { FloorRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl() }
}