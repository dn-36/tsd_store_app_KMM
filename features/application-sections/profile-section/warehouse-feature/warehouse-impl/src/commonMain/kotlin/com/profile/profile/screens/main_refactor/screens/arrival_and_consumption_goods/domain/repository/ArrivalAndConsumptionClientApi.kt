package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

interface ArrivalAndConsumptionClientApi {

    suspend fun getContragents(onGet: (newListContragents: List<ContragentResponseArrivalAndConsumption>) -> Unit)

    suspend fun getWarehouseArrivalAndConsumption(onGet: (listAllWarehouse: List<WarehouseArrivalAndConsumption>) -> Unit)

    suspend fun getProducts(onGet: (listAllProducts: List<AllProductArrivalAndConsumption>) -> Unit)
    suspend fun deleteArrivalOrConsumption( ui:String )
    suspend fun createArrivalOrConsumption(

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun updateArrivalOrConsumption(

        productUi: String ,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun getArrivalAndConsumption(onGet: (listArrivalAndConsumption: List<StoreResponseArrivalAndConsumption>) -> Unit)

}