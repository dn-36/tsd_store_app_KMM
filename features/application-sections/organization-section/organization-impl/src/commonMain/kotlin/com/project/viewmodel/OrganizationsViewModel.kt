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
import com.project.network.Navigation
import com.project.network.organizations_network.OrganizationsApi
import com.project.screen.OrganizationScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationsViewModel (val createOrganizationUseCase: CreateOrganizationUseCase

 , val deleteOrganizationUseCase: DeleteOrganizationUseCase

, val choosingActiveOrganization: ChoosingActiveOrganizationUseCase

):ViewModel() {

    var organizationsState by mutableStateOf(OrganizationsState())

    fun processIntent(intent: OrganizationsIntents){

        when(intent){

            is OrganizationsIntents.SetScreen -> setScreen(intent.coroutineScope)

            is OrganizationsIntents.ChoosingActiveOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    choosingActiveOrganization.execute(intent.ui,intent.coroutineScope)

                    organizationsState = organizationsState.copy(

                        isUsed = mutableStateOf(true)

                    )

                }

            }//choosingActiveOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.DeleteOrganization -> {

                intent.coroutineScope.launch (Dispatchers.IO) {

                    deleteOrganizationUseCase.execute(intent.ui,intent.coroutineScope)

                    organizationsState = organizationsState.copy(

                        isUsed = mutableStateOf(true)

                    )

                }
            }//deleteOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.CreateOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    createOrganizationUseCase.execute(intent.name,intent.url,

                        intent.coroutineScope, onCreate = { Navigation.navigator.push(OrganizationScreen())

                        organizationsState = organizationsState.copy(

                            isUsed = mutableStateOf(true)

                        )})

                }
            }//createOrganization(intent.coroutineScope,intent.name,intent.url)

            is OrganizationsIntents.OpenWindowAddOrganization -> openWindowAddOrganization()

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

    fun createOrganization(coroutineScope: CoroutineScope,name: String, url: String){

        val token = ConstData.TOKEN

        OrganizationsApi.token = token

        coroutineScope.launch(Dispatchers.IO) {

            OrganizationsApi.createOrganization(name,url)


            Navigation.navigator.push(OrganizationScreen())

            organizationsState = organizationsState.copy(
                isUsed = mutableStateOf(true)
            )
        }
    }

    fun openWindowAddOrganization(){

    Navigation.navigator.push(CreateOrganization(onClick = {scope, name, url ->
        processIntent(OrganizationsIntents.CreateOrganization(scope,name,url))
    }))

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