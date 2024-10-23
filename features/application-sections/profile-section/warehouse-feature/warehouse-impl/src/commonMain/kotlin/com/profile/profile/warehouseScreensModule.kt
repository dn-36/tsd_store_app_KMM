package com.profile.profile

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.datasource.ArrivalAndConsumptionClientImpl
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository.ArrivalAndConsumptionClientApi
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.CreateArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.DeleteArrivalOrConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetContagentsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetProductsUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.usecases.GetWarehouseArrivalAndConsumptionUseCase
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import com.profile.profile.screens.main_refactor.screens.warehouse.datasource.WarehouseClientImpl
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.repository.WarehouseClientApi
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.CreateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.DeleteWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetLocationsUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.GetWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.domain.usecases.UpdateWarehouseUseCase
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseViewModel
import com.project.chats.WarehouseScreensApi
import com.project.network.ConstData
import com.project.network.arrival_goods.ArrivalGoodsClient
import com.project.network.contragent_network.ContragentClient
import com.project.network.locations_network.LocationsClient
import com.project.network.warehouse_network.WarehouseClient
import org.koin.dsl.module
import product_network.ProductApiClient

val warehouseScreensModule = module {

   factory { WarehouseScreensImpl() as WarehouseScreensApi }

   factory { WarehouseClient() }

   factory { ContragentClient() }

   factory { ArrivalGoodsClient() }

   factory { ProductApiClient(ConstData.TOKEN) }

   factory { LocationsClient() }

   factory { UpdateWarehouseUseCase( get()) }

   factory { DeleteWarehouseUseCase( get()) }

   factory { GetWarehouseUseCase( get()) }

   factory { GetProductsUseCase( get()) }

   factory { CreateWarehouseUseCase( get()) }

   factory { CreateArrivalOrConsumptionUseCase( get()) }

   factory { GetArrivalAndConsumptionUseCase( get()) }

   factory { GetContagentsUseCase( get()) }

   factory { GetWarehouseArrivalAndConsumptionUseCase( get()) }

   factory { GetLocationsUseCase( get()) }

   factory { DeleteArrivalOrConsumptionUseCase( get()) }

   factory { WarehouseViewModel( get(), get(), get(), get(), get()) }

   factory { ArrivalAndConsumptionViewModel( get(), get(), get(), get(), get(), get()) }

    factory { WarehouseClientImpl(get(),get()) as WarehouseClientApi }

    factory { ArrivalAndConsumptionClientImpl(get(),get(), get(), get()) as ArrivalAndConsumptionClientApi }

}