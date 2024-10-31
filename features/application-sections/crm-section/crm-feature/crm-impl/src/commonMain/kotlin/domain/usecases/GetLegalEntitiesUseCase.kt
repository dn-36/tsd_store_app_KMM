package domain.usecases

import domain.repository.CRMClientApi
import model.EntityContragentModel

class GetLegalEntitiesUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): List<EntityContragentModel> {

        return client.getLegalEntities ()
    }
}