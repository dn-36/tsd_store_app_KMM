package domain.usecases

import domain.repository.CRMClientApi

class GetIncomingCRMUseCase (

    private val client: CRMClientApi,

    ) {

    suspend fun execute(  ): String {

        return client.getIncomingCRM ()
    }
}