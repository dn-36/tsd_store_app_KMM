package domain.usecases

import domain.repository.CRMClientApi
import model.SpecificResponseModel

class GetSpecificationsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<SpecificResponseModel> {

        return client.getSpecifications ()
    }
}