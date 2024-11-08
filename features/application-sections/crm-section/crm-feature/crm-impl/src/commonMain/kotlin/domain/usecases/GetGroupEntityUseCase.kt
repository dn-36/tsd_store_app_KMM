package domain.usecases

import domain.repository.CRMClientApi
import model.ApiResponseCRMModel
import model.GroupEntityResponseModel

class GetGroupEntityUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute ( ): List<GroupEntityResponseModel> {

        return client.getGroupEntity ()

    }
}