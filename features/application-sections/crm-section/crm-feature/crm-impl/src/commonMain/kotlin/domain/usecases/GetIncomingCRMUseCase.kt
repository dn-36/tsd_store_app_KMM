package domain.usecases

import domain.repository.CRMClientApi
import model.ApiResponseCRMModel

class GetIncomingCRMUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): List<ApiResponseCRMModel> {

        return client.getIncomingCRM ()
    }
}