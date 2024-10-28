package com.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.project.component.CreateOrUpdateOrganization
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.domain.usecases.ChoosingActiveOrganizationUseCase
import com.project.domain.usecases.CreateOrganizationUseCase
import com.project.domain.usecases.DeleteOrganizationUseCase
import com.project.domain.usecases.GetOrganizationUseCase
import com.project.domain.usecases.UpdateOrganizationUseCase
import com.project.network.Navigation
import com.project.network.organizations_network.model.Response
import com.project.screen.OrganizationScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class OrganizationsViewModel(

    val createOrganizationUseCase: CreateOrganizationUseCase,

    val deleteOrganizationUseCase: DeleteOrganizationUseCase,

    val choosingActiveOrganization: ChoosingActiveOrganizationUseCase,

    val getOrganization: GetOrganizationUseCase,

    val updateOrganization: UpdateOrganizationUseCase

) : NetworkViewModel() {

    var state by mutableStateOf(OrganizationsState())

    fun processIntent(intent: OrganizationsIntents) {

        when (intent) {

            is OrganizationsIntents.SetScreen -> {

                if (state.isUsed.value) {

                    state.isUsed.value = false

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        getOrganization.execute ( onGet = { listColor, listOrganization ->

                            state = state.copy(

                                allOrganizations = listOrganization,

                                listColorActiveOrganizations = listColor
                            )

                        })

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }

                }

            }//setScreen(intent.coroutineScope)

            is OrganizationsIntents.ChoosingActiveOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    choosingActiveOrganization.execute(intent.ui,

                        onChoosing = {
                            state = state.copy(

                                isUsed = mutableStateOf(true)

                            )
                        })

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }

            }//choosingActiveOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.DeleteOrganization -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteOrganizationUseCase.execute(intent.ui,
                        onDelete = {
                            state = state.copy(

                                isUsed = mutableStateOf(true)

                            )
                        })

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }
            }//deleteOrganization(intent.coroutineScope,intent.ui)

            is OrganizationsIntents.CreateOrganization -> {

                if( intent.name.isNotBlank() && intent.url.isNotBlank() ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        createOrganizationUseCase.execute(intent.name, intent.url,

                            onCreate = {

                                Navigation.navigator.push(OrganizationScreen())

                                state = state.copy(

                                    isUsed = mutableStateOf(true)

                                )
                            })

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }

                }
            }//createOrganization(intent.coroutineScope,intent.name,intent.url)

            is OrganizationsIntents.OpenWindowAddOrganization -> openWindowAddOrganization()

            is OrganizationsIntents.UpdateOrganization -> {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                       // if ( intent.name.isNotBlank() && intent.url.isNotBlank() ) {

                            updateOrganization.execute(name = intent.name, url = intent.url,

                                ui = intent.ui, onUpdate = {

                                    Navigation.navigator.push(OrganizationScreen())

                                    state = state.copy(

                                        isUsed = mutableStateOf(true)

                                    )

                                }

                            )

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }

            }

            is OrganizationsIntents.SelectItemUpdate -> { selectItemUpdate( intent.item ) }

        }
    }

    fun openWindowAddOrganization() {

        Navigation.navigator.push(CreateOrUpdateOrganization(onClickCreate = { scope, name, url ->

            processIntent(OrganizationsIntents.CreateOrganization(scope, name, url))

        }, onClickUpdate = { scope, name, url -> } ,

            isUpdate = state.isUpdateOrganization, listBorderColor = state.listColorBorderTf, item = null ))

        state = state.copy(

            isUpdateOrganization = false

        )

    }

    fun selectItemUpdate ( item: Response ) {

        println("URL ORGANIZATION: ${item}")

    state = state.copy(

        updateOrganization = item,

        isUpdateOrganization = true

    )
        Navigation.navigator.push(CreateOrUpdateOrganization(onClickCreate = { scope, name, url ->

            processIntent(OrganizationsIntents.CreateOrganization(scope, name, url))

        }, onClickUpdate = { scope, name, url ->

        processIntent(OrganizationsIntents.UpdateOrganization( coroutineScope = scope,

            name = name, url = url, ui = state.updateOrganization!!.company?.ui?:"" ))}

            , isUpdate = state.isUpdateOrganization, listBorderColor = state.listColorBorderTf, item = state.updateOrganization ))

    }

}