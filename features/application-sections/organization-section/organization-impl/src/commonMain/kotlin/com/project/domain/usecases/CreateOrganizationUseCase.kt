package com.project.domain.usecases

import com.project.domain.repository.OrganizationClientApi
import kotlinx.coroutines.CoroutineScope

class CreateOrganizationUseCase (

    private val client: OrganizationClientApi,

) {

    suspend fun execute( name: String,
                          url:String,
                         onCreate: () -> Unit

    ){

        client.createOrganization (
            name, url, onCreate
        )
    }
}