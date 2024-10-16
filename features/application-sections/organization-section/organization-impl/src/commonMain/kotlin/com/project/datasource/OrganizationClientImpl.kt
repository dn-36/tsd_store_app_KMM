package com.project.datasource

import com.project.core_app.ConstData
import com.project.domain.repository.OrganizationClientApi
import com.project.network.organizations_network.OrganizationsClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationClientImpl (

    private val organizationClient: OrganizationsClient

) : OrganizationClientApi{


    override suspend fun createOrganization(name: String, url: String, scope: CoroutineScope
    ,onCreate: () -> Unit) {

        val token = ConstData.TOKEN

        OrganizationsClient.token = token

        scope.launch(Dispatchers.IO) {

            organizationClient.createOrganization(name,url)

            onCreate()

        }

    }

    override suspend fun deleteOrganization( ui: String, scope: CoroutineScope, onDelete: () -> Unit) {

        val token = ConstData.TOKEN

        OrganizationsClient.token = token

        scope.launch(Dispatchers.IO) {

            organizationClient.deleteOrganization(ui)

            onDelete()

        }

    }

    override suspend fun choosingActiveOrganization(ui:String, scope: CoroutineScope, onChoosing: () -> Unit){

        val token = ConstData.TOKEN

        OrganizationsClient.token = token

        scope.launch(Dispatchers.IO) {

            organizationClient.setActiveOrganization(ui)

            onChoosing()

        }
    }

}