package com.project.datasource

import com.project.core_app.ConstData
import com.project.domain.repository.OrganizationClientApi
import com.project.network.organizations_network.OrganizationsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationClientImpl (

    private val organizationClient: OrganizationsApi

) : OrganizationClientApi{

    override suspend fun createOrganization(name: String, url: String, scope: CoroutineScope
    ,onCreate: () -> Unit) {

        val token = ConstData.TOKEN

        organizationClient.token = token

        scope.launch(Dispatchers.IO) {

            organizationClient.createOrganization(name,url)

            onCreate()

                // Navigation.navigator.push(OrganizationScreen())

            /*organizationsState = organizationsState.copy(
                isUsed = mutableStateOf(true)
            )*/
        }

    }

}