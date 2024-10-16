package com.project.domain.usecases

import com.project.domain.repository.OrganizationClientApi
import kotlinx.coroutines.CoroutineScope

class ChoosingActiveOrganizationUseCase (

    private val client: OrganizationClientApi,

    ) {

    suspend fun execute( ui:String,
                         scope: CoroutineScope

    ){

        client.choosingActiveOrganization (
            ui, scope
        )
    }
}