package com.domain.repository

import com.model.ContragentResponseModel
import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

interface SpecificationsClientApi {

    suspend fun getSpecifications(): List<SpecificResponseModel>

    suspend fun getContragents(): List<ContragentResponseModel>

    suspend fun getCurrency(): List<CurrencyResponseModel>

    suspend fun getWarehouse(): List<WarehouseModel>

    suspend fun getProducts(): List<ProductResponseModel>

    suspend fun getToken():String

}