package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.project.network.arrival_goods.model.ProductArrival
import com.project.network.arrival_goods.model.StoreResponse
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.warehouse_network.model.Warehouse
import product_network.model.Product

interface ArrivalAndConsumptionClientApi {

    suspend fun getContragents(onGet: (newListContragents: List<ContragentResponse>) -> Unit)

    suspend fun getWarehouseArrivalAndConsumption(onGet: (listAllWarehouse: List<Warehouse>) -> Unit)

    suspend fun getProducts(onGet: (listAllProducts: List<Product>) -> Unit)
    suspend fun createArrivalOrConsumption(

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun getArrivalAndConsumption(onGet: (listArrivalAndConsumption: List<StoreResponse>) -> Unit)

}