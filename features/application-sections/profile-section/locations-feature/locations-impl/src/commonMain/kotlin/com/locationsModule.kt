package com

import com.datasource.LocationClientImpl
import com.domain.repository.LocationsClientApi
import com.domain.usecases.CreateLocationUseCase
import com.domain.usecases.DeleteLocationUseCase
import com.domain.usecases.GetContragentUseCase
import com.domain.usecases.GetLocationsUseCase
import com.domain.usecases.UpdateLocationUseCase
import com.project.network.contragent_network.ContragentClient
import com.project.network.locations_network.LocationsClient
import com.viewmodel.LocationsViewModel
import org.koin.dsl.module

val locationsModule = module {

    factory { LocationsScreenImpl() as LocationsScreenApi }

    factory { LocationClientImpl(get(), get(), get()) as LocationsClientApi }

    factory { GetLocationsUseCase(get()) }

    factory { CreateLocationUseCase(get()) }

    factory { GetContragentUseCase(get()) }

    factory { CreateLocationUseCase(get()) }

    factory { DeleteLocationUseCase(get()) }

    factory { UpdateLocationUseCase(get()) }

    factory { LocationsViewModel(get(), get(), get(), get(), get()) }

    factory { LocationsClient() }

    factory { ContragentClient() }

}