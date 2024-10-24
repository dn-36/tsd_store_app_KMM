package com.project.domain.usecases

import com.project.domain.repository.OrganizationClientApi

class UpdateOrganizationUseCase (

    private val client: OrganizationClientApi,

    ) {

    suspend fun execute(
                         name: String,

                         url: String,

                         onUpdate: () -> Unit,

                         ui: String

    ){

        client.updateOrganization (

            name, url, ui, onUpdate

        )
    }
}