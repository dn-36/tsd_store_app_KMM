package screen.domain.usecases

import screen.domain.repository.ContragentsClientApi

class DeleteContragentUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute ( id: Int ) {

    client.deleteContragents (

        id

    )

    }
}