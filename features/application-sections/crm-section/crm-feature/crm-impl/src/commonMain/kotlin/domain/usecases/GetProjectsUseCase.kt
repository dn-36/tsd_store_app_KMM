package domain.usecases

import domain.repository.CRMClientApi
import model.ProjectResponseModel
import model.ServiceResponseModel

class GetProjectsUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): List<ProjectResponseModel> {

        return client.getProjects ()
    }
}