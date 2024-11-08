package domain.usecases

import domain.repository.CRMClientApi
import model.ApiResponseCRMModel

class GetOutgoingCRMUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<ApiResponseCRMModel> {

        return client.getOutgoingCRM ()
    }
}