package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ElementSpecification

class UpdateSpecificationUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute(

        ui:String,
        text: String?,
        valuta_id: Int?,
        local_store_id: Int?,
        price: Int?,
        status: Int?,
        items: List<ElementSpecification>?
    ) {

        return client.updateSpecifications( ui,text, valuta_id, local_store_id,

            price, status, items )

    }
}