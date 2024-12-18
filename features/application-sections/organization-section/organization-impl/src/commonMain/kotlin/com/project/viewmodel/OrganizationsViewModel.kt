package com.project.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

                                listOrganizations = listOrganization,

                                listFilteredOrganizations = listOrganization,

                                listColorActiveOrganizations = listColor
                            )

                        })

                        setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                    }

                }

            }

            is OrganizationsIntents.ChoosingActiveOrganization -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch(Dispatchers.IO) {

                    choosingActiveOrganization.execute(intent.ui,

                        onChoosing = {
                            state = state.copy(

                                isUsed = mutableStateOf(true)

                            )
                        })

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }

            }

            is OrganizationsIntents.DeleteOrganization -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                intent.coroutineScope.launch(Dispatchers.IO) {

                    deleteOrganizationUseCase.execute( state.updateOrganization!!.company!!.ui!!,
                        onDelete = {
                            state = state.copy(

                                isUsed = mutableStateOf(true),

                                isVisibilityDeleteComponent = 0f

                            )
                        })

                    setStatusNetworkScreen ( StatusNetworkScreen.SECCUESS )

                }
            }

            is OrganizationsIntents.CreateOrganization -> {

                if( intent.name.isNotBlank() && intent.url.isNotBlank() ) {

                    setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

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
            }

            is OrganizationsIntents.OpenWindowAddOrganization -> openWindowAddOrganization()

            is OrganizationsIntents.UpdateOrganization -> {

                setStatusNetworkScreen ( StatusNetworkScreen.LOADING )

                    intent.coroutineScope.launch(Dispatchers.IO) {

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

            is OrganizationsIntents.CancelOrganizationComponent -> { cancel() }

            is OrganizationsIntents.NoDelete -> { noDelete() }

            is OrganizationsIntents.OpenDeleteComponent -> { openDeleteComponent( intent.item ) }

            is OrganizationsIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)
            }

            is OrganizationsIntents.LongPressItem -> longPressItem( intent.index )

            is OrganizationsIntents.OnePressItem -> onePressItem()

        }
    }

    fun openWindowAddOrganization() {

        Navigation.navigator.push(CreateOrUpdateOrganization(onClickCreate = { scope, name, url ->

            processIntent(OrganizationsIntents.CreateOrganization(scope, name, url))

        }, onClickUpdate = { scope, name, url -> },

            onClickCansel = { processIntent( OrganizationsIntents.CancelOrganizationComponent ) } ,

            isUpdate = state.isUpdateOrganization, listBorderColor = state.listColorBorderTf,

            item = null ))

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

            name = name, url = url, ui = state.updateOrganization!!.company?.ui?:"" ))},

            onClickCansel = { processIntent( OrganizationsIntents.CancelOrganizationComponent ) }

            , isUpdate = state.isUpdateOrganization, listBorderColor = state.listColorBorderTf,

            item = state.updateOrganization ))

    }

    fun cancel () {

        Navigation.navigator.push(OrganizationScreen())

    }

    fun openDeleteComponent ( item: Response ) {

    state = state.copy(

        isVisibilityDeleteComponent = 1f,

        updateOrganization = item

    )

    }

    fun noDelete () {

        state = state.copy(

            isVisibilityDeleteComponent = 0f,

            updateOrganization = null

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listOrganizations.filter {

            val companyName = it.company?.name.orEmpty()

            companyName.contains(text, ignoreCase = true)

        }

        state = state.copy(

            listFilteredOrganizations = newList

        )

        println("Text ${text}")
        println("NewList ${newList}")

    }

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listOrganizations.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listOrganizations.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}