package com

import com.datasource.LocationClientImpl
import com.domain.repository.LocationsClientApi
import com.domain.usecases.DeleteLocationUseCase
import com.domain.usecases.GetLocationsUseCase
import com.project.network.locations_network.LocationsClient
import com.viewmodel.LocationsViewModel
import org.koin.dsl.module

val locationsModule = module {

    factory { LocationsScreenImpl() as LocationsScreenApi }

    factory { LocationClientImpl(get(), get()) as LocationsClientApi }

    factory { GetLocationsUseCase(get()) }

    factory { DeleteLocationUseCase(get()) }

    factory { LocationsViewModel(get(), get()) }

    factory { LocationsClient() }

}