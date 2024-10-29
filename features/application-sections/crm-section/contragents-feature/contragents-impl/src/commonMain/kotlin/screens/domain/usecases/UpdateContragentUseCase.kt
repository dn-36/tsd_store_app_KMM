package screens.domain.usecases

import screens.domain.repository.ContragentsClientApi

class UpdateContragentUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute( name: String, id: Int ) {

        client.updateContragent(

            name, id

        )

    }
}