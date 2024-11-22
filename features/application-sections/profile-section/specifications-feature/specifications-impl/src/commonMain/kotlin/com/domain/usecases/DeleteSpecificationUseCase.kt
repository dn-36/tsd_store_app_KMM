package com.domain.usecases

import com.domain.repository.SpecificationsClientApi
import com.model.ElementSpecification

class DeleteSpecificationUseCase (

    private val client: SpecificationsClientApi,

    ) {

    suspend fun execute(

        ui: String
    ) {

        return client.deleteSpecifications( ui )

    }
}