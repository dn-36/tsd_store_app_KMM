package com

import com.datasoutrce.SpecificationsClientImpl
import com.domain.repository.SpecificationsClientApi
import com.domain.usecases.GetContragentsUseCase
import com.domain.usecases.GetCurrencyUseCase
import com.domain.usecases.GetProductUseCase
import com.domain.usecases.GetSpecificationsUseCase
import com.domain.usecases.GetWarehouseUseCase
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.ConstData
import com.project.network.contragent_network.ContragentClient
import com.project.network.specifications_network.SpecificationsClient
import com.project.network.valuta_network.model.CurrencyClient
import com.project.network.warehouse_network.WarehouseClient
import com.viewmodel.SpecificationsViewModel
import org.koin.dsl.module
import product_network.ProductApiClient

val specificationsModule = module {

    factory { SpecificationsScreenImpl() as SpecificationsScreenApi }

    factory { SpecificationsClientImpl(get(), get(), get(), get(), get(), get()) as SpecificationsClientApi }

    factory { GetSpecificationsUseCase(get()) }

    factory { GetProductUseCase(get()) }

    factory { GetWarehouseUseCase(get()) }

    factory { GetCurrencyUseCase(get()) }

    factory { GetContragentsUseCase(get()) }

    factory { SpecificationsViewModel(get(), get(), get(), get(), get()) }

    factory { SpecificationsClient() }

    factory { ContragentClient() }

    factory { CurrencyClient() }

    factory { WarehouseClient() }

    factory { ProductApiClient(ConstData.TOKEN) }
}