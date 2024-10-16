package com.project.domain.usecases

import com.project.domain.repository.OrganizationClientApi
import kotlinx.coroutines.CoroutineScope

class DeleteOrganizationUseCase (

    private val client: OrganizationClientApi,

    ) {

    suspend fun execute( ui:String,
                         scope: CoroutineScope

    ){

        client.deleteOrganization (
            ui, scope
        )
    }
}