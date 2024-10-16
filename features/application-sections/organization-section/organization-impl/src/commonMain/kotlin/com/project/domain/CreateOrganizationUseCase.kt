package com.project.domain

import com.project.domain.repository.OrganizationClientApi
import com.project.network.organizations_network.OrganizationsApi
import kotlinx.coroutines.CoroutineScope

class CreateOrganizationUseCase (
    private val client: OrganizationClientApi,

) {

    suspend fun execute( name: String,
                          url:String

    ){

        client.createOrganization (
            name, url
        )
    }
}