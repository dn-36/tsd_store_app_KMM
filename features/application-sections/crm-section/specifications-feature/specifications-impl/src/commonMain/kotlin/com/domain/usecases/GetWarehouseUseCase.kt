package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.SpecificResponseModel
import com.model.WarehouseModel

class GetWarehouseUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute ( ): List<WarehouseModel> {

        return client.getWarehouse ()

    }
}