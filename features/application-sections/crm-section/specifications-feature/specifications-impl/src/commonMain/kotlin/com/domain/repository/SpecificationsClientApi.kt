package com.domain.repository

import com.model.ContragentResponseModel
import com.model.CurrencyResponseModel
import com.model.ElementSpecification
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

interface SpecificationsClientApi {

    suspend fun getToken(): String

    suspend fun getSpecifications(): List<SpecificResponseModel>

    suspend fun getContragents(): List<ContragentResponseModel>

    suspend fun getCurrency(): List<CurrencyResponseModel>

    suspend fun getWarehouse(): List<WarehouseModel>

    suspend fun getProducts(): List<ProductResponseModel>

    suspend fun deleteSpecifications( ui: String )

    suspend fun createSpecifications(

        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items:List<ElementSpecification>?

    )

    suspend fun updateSpecifications(

        ui: String,
        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items:List<ElementSpecification>?

    )

}