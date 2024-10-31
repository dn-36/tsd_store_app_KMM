package domain.usecases

import domain.repository.CRMClientApi
import model.UserCRMModel

class CreateCRMUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): List<UserCRMModel> {

        return client.getUsers ()
    }
}