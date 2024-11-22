package screen.domain.usecases

import screen.domain.repository.ContragentsClientApi
import screen.model.ContragentResponseModel

class GetContragentsUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute ( ): List<ContragentResponseModel> {

        return client.getContragents ( )
    }
}