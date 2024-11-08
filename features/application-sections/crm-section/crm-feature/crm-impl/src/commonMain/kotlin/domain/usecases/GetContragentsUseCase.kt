package domain.usecases

import domain.repository.CRMClientApi
import model.ContragentResponseModel
import model.EntityContragentModel

class GetContragentsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<ContragentResponseModel> {

        return client.getContragents ()
    }
}