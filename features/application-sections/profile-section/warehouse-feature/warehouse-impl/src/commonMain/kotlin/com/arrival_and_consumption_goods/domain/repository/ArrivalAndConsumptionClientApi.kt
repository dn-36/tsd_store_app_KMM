package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.domain.repository

import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

interface ArrivalAndConsumptionClientApi {

    suspend fun getContragents(): List<ContragentResponseArrivalAndConsumption>

    suspend fun getWarehouseArrivalAndConsumption(): List<WarehouseArrivalAndConsumption>

    suspend fun getProducts(): List<AllProductArrivalAndConsumption>

    suspend fun deleteArrivalOrConsumption( ui:String )

    suspend fun createArrivalOrConsumption(

        description: String,

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

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?,

        isPush: Int ,

        istProducts: List<ProductArrivalAndConsumption>

    )

    suspend fun getArrivalAndConsumption(): List<StoreResponseArrivalAndConsumption>

}