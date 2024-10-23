package com.project.domain.usecases

import androidx.compose.ui.graphics.Color
import com.project.domain.repository.OrganizationClientApi
import com.project.network.organizations_network.model.Response

class GetOrganizationUseCase (

    private val client: OrganizationClientApi,

    ) {

    suspend fun execute( onGet: (listColor:MutableList<Color>, listOrganization:List<Response>) -> Unit ) {

        client.getOrganizations (
            onGet
        )
    }
}