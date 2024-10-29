package screens.domain.usecases

import screens.domain.repository.ContragentsClientApi

class CreateContragentUseCase (

    private val client: ContragentsClientApi,

    ) {

    suspend fun execute(name: String) {

        client.createContragents(

            name

        )

    }
}