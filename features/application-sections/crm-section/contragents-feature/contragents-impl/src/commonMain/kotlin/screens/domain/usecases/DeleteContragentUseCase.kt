package screens.domain.usecases

import screens.domain.repository.ContragentsClientApi
import screens.model.ContragentResponseModel

class DeleteContragentUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute ( id: Int ) {

    client.deleteContragents (

        id

    )

    }
}