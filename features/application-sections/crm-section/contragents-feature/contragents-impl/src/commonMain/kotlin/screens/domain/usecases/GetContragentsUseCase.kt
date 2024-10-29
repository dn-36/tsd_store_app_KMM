package screens.domain.usecases

import screens.domain.repository.ContragentsClientApi
import screens.model.ContragentResponseModel

class GetContragentsUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute ( ): List<ContragentResponseModel> {

        return client.getContragents ( )
    }
}