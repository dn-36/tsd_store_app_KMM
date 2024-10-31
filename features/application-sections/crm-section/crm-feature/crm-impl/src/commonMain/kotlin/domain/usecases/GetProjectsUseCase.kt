package domain.usecases

import domain.repository.CRMClientApi
import model.ServiceResponseModel

class GetProjectsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): String {

        return client.getProjects ()
    }
}