package com.project.datasource

import androidx.compose.ui.graphics.Color
import com.project.core_app.ConstData
import com.project.domain.repository.OrganizationClientApi
import com.project.network.locations_network.LocationsClient
import com.project.network.organizations_network.OrganizationsClient
import com.project.network.organizations_network.model.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationClientImpl (

    private val organizationClient: OrganizationsClient

) : OrganizationClientApi{


    override suspend fun createOrganization(name: String, url: String, onCreate: () -> Unit) {

            organizationClient.createOrganization(name,url)

            onCreate()
    }

    override suspend fun deleteOrganization( ui: String, onDelete: () -> Unit) {

            organizationClient.deleteOrganization(ui)

            onDelete()

    }

    override suspend fun choosingActiveOrganization(ui:String, onChoosing: () -> Unit){

            organizationClient.setActiveOrganization(ui)

            onChoosing()

    }

    override suspend fun getOrganizations (onGet: ( listColor:MutableList<Color>, listOrganization: List<Response>) -> Unit){

        val allOrganizations = organizationClient.getOrganizations()

        println(
            "-------${allOrganizations}--------------"
        )

        val newListColor = mutableListOf<Color>()

        allOrganizations.forEach { item ->

            if(item.active == 0){
                newListColor.add(Color.LightGray)
            }
            else {

                newListColor.add(Color.Green)

            }

        }

        onGet ( newListColor, allOrganizations )

    }

}