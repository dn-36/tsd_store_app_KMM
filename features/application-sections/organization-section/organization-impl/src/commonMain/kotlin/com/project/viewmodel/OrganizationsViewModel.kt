package com.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.project.component.CreateOrganization
import com.project.core_app.ConstData
import com.project.domain.usecases.ChoosingActiveOrganizationUseCase
import com.project.domain.usecases.CreateOrganizationUseCase
import com.project.domain.usecases.DeleteOrganizationUseCase
import com.project.domain.usecases.GetOrganizationUseCase
import com.project.network.Navigation
import com.project.network.organizations_network.OrganizationsClient
import com.project.screen.OrganizationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationsViewModel (

    val createOrganizationUseCase: CreateOrganizationUseCase,

    val deleteOrganizationUseCase: DeleteOrganizationUseCase,

    val choosingActiveOrganization: ChoosingActiveOrganizationUseCase,

    val getOrganization: GetOrganizationUseCase

) :ViewModel() {

    var organizationsState by mutableStateOf(OrganizationsState())

    fun processIntent(intent: OrganizationsIntents){

        when(intent){

            is OrganizationsIntents.SetScreen -> {

                if(organizationsState.isUsed.value) {

                    organizationsState.isUsed.value = false

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        getOrganization.execute(onGet = { listColor, listOrganization ->

                            organizationsState = organizationsState.copy(

                                allOrganizations = listOrganization,

                                listColorActiveOrganizations = listColor
                            )

                        })
                    }
                }

            }//setScreen(intent.coroutineScope)

            is OrganizationsIntents.ChoosingActiveOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    choosingActiveOrganization.execute(intent.ui,

                        onChoosing = {organizationsState = organizationsState.copy(

                            isUsed = mutableStateOf(true)

                        )
                    })

                }

            }//choosingActiveOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.DeleteOrganization -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteOrganizationUseCase.execute(intent.ui,
                        onDelete = { organizationsState = organizationsState.copy(

                            isUsed = mutableStateOf(true)

                        ) } )

                }
            }//deleteOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.CreateOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    createOrganizationUseCase.execute(intent.name,intent.url,

                        onCreate = { Navigation.navigator.push(OrganizationScreen())

                        organizationsState = organizationsState.copy(

                            isUsed = mutableStateOf(true)

                        )})

                }
            }//createOrganization(intent.coroutineScope,intent.name,intent.url)

            is OrganizationsIntents.OpenWindowAddOrganization -> openWindowAddOrganization()

        }
    }

    fun openWindowAddOrganization(){

    Navigation.navigator.push(CreateOrganization(onClick = {scope, name, url ->
        processIntent(OrganizationsIntents.CreateOrganization(scope,name,url))
    }))

    }
}