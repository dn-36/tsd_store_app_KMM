package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ProductResponseModel
import com.model.SpecificResponseModel

data class GetProductUseCase(

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute ( ): List<ProductResponseModel> {

        return client.getProducts ()

    }
}
