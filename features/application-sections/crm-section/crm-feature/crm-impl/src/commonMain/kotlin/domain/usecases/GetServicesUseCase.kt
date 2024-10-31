package domain.usecases

import domain.repository.CRMClientApi
import model.ApiResponseCRMModel
import model.ServiceResponseModel

class GetServicesUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): List<ServiceResponseModel> {

        return client.getServices ()
    }
}