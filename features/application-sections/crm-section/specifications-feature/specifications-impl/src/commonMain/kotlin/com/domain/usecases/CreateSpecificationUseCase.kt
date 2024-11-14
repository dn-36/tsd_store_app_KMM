package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ContragentResponseModel
import com.model.ElementSpecification

class CreateSpecificationUseCase(

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute(

        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<ElementSpecification>?
    ) {

        return client.createSpecifications( text, valuta_id, local_store_id,

            price, status, items )

    }
}