package com.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.project.core_app.ConstData
import com.project.network.organizations_network.OrganizationsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationsViewModel:ViewModel() {

    var organizationsState by mutableStateOf(OrganizationsState())

    fun processIntent(intent: OrganizationsIntents){
        when(intent){

            is OrganizationsIntents.SetScreen -> setScreen(intent.coroutineScope)

            is OrganizationsIntents.ChoosingActiveOrganization -> choosingActiveOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.DeleteOrganization -> deleteOrganization(intent.coroutineScope,intent.ui)

        }
    }

    fun choosingActiveOrganization(coroutineScope: CoroutineScope,ui:String){

        val token = ConstData.TOKEN

        OrganizationsApi.token = token

        coroutineScope.launch(Dispatchers.IO) {

        OrganizationsApi.setActiveOrganization(ui)

            organizationsState = organizationsState.copy(
                isUsed = mutableStateOf(true)
            )
        }
    }

    fun deleteOrganization(coroutineScope: CoroutineScope,ui:String){

        val token = ConstData.TOKEN

        OrganizationsApi.token = token

        coroutineScope.launch(Dispatchers.IO) {

            OrganizationsApi.deleteOrganization(ui)

            organizationsState = organizationsState.copy(
                isUsed = mutableStateOf(true)
            )
        }
    }

    fun setScreen(coroutineScope: CoroutineScope){

        if(organizationsState.isUsed.value) {

            organizationsState.isUsed.value = false

            val token = ConstData.TOKEN

            OrganizationsApi.token = token

            coroutineScope.launch(Dispatchers.IO) {

                val allOrganizations = OrganizationsApi.getOrganizations()

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
                organizationsState = organizationsState.copy(
                    allOrganizations = allOrganizations,
                    listColorActiveOrganizations = newListColor
                )

            }
        }
    }
}