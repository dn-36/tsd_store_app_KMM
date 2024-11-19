package org.example.project.presentation.project_control.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import com.project.network.notes_network.model.removeHtmlTags
import com.project.project_conterol.domain.usecases.CreateProjectControlUseCase
import com.project.project_conterol.domain.usecases.DeleteProjectControlUseCase
import com.project.project_conterol.domain.usecases.GetProjectsControlUseCase
import com.project.project_conterol.domain.usecases.GetProjectsUseCase
import com.project.project_conterol.domain.usecases.UpdateProjectControlUseCase
import com.project.project_conterol.model.ServiceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class ProjectControlViewModel (

    val getProjectsControlUseCase: GetProjectsControlUseCase,

    val getProjectsUseCase: GetProjectsUseCase,

    val createProjectControlUseCase: CreateProjectControlUseCase,

    val updateProjectControlUseCase: UpdateProjectControlUseCase,

    val deleteProjectControlUseCase: DeleteProjectControlUseCase

): NetworkViewModel() {

    var state by mutableStateOf(ProjectControlState())

    fun processIntents ( intent: ProjectControlIntents ) {

        when ( intent ) {

            is ProjectControlIntents.Back -> back()

            is ProjectControlIntents.OpenDescription -> openDescription(intent.index)

            is ProjectControlIntents.OpenCreateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    state = state.copy(

                        listProjects = getProjectsUseCase.execute(),

                        isVisibilityDataEntryComponent = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ProjectControlIntents.OpenUpdateDataEntryComponent -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    state = state.copy(

                        listProjects = getProjectsUseCase.execute(),

                        updatedItem = intent.item,

                        isVisibilityDataEntryComponent = true

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ProjectControlIntents.SetScreen -> {

                if ( state.isSet ) {

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        val listAllProjectsControl = getProjectsControlUseCase.execute()

                        val cleanedProjectsControl = listAllProjectsControl.copy(

                            data = listAllProjectsControl.data?.map { it ->

                            it.copy(text = it.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
                        }

                        )

                        state = state.copy(

                            listProjectsControl = cleanedProjectsControl,

                            listFilteredProjectsControl = cleanedProjectsControl,

                            isSet = false

                        )

                        if ( state.listProjectsControl != null &&

                            state.listProjectsControl!!.data != null ) {

                            val newList = List(state.listProjectsControl!!.data!!.size) { false }

                            state = state.copy(

                                listExpendedDescription = newList

                            )

                        }

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }
                }

            }

            is ProjectControlIntents.BackFromDataEntry -> backFromDataEntry()

            is ProjectControlIntents.CreateProjectControl -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

            intent.coroutineScope.launch ( Dispatchers.IO ) {

             createProjectControlUseCase.execute( text = intent.text, data = intent.data,

                 time = intent.time, project_id = intent.project_id)

                val listAllProjectsControl = getProjectsControlUseCase.execute()

                val cleanedProjectsControl = listAllProjectsControl.copy(

                    data = listAllProjectsControl.data?.map { it ->

                        it.copy(text = it.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
                    }

                )

                state = state.copy(

                    listProjectsControl = cleanedProjectsControl,

                    listFilteredProjectsControl = cleanedProjectsControl,

                    isVisibilityDataEntryComponent = false,

                    updatedItem = null

                )

                setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

            }

            }

            is ProjectControlIntents.UpdateProjectControl -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    updateProjectControlUseCase.execute( id = intent.id, text = intent.text,

                        data = intent.data, time = intent.time, project_id = intent.project_id)

                    val listAllProjectsControl = getProjectsControlUseCase.execute()

                    val cleanedProjectsControl = listAllProjectsControl.copy(

                        data = listAllProjectsControl.data?.map { it ->

                            it.copy(text = it.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
                        }

                    )

                    state = state.copy(

                        listProjectsControl = cleanedProjectsControl,

                        listFilteredProjectsControl = cleanedProjectsControl,

                        isVisibilityDataEntryComponent = false,

                        updatedItem = null

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ProjectControlIntents.DeleteProjectControl -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch ( Dispatchers.IO ) {

                    deleteProjectControlUseCase.execute( state.updatedItem!!.id?:0 )

                    val listAllProjectsControl = getProjectsControlUseCase.execute()

                    val cleanedProjectsControl = listAllProjectsControl.copy(

                        data = listAllProjectsControl.data?.map { it ->

                            it.copy(text = it.text?.let { removeHtmlTags(it) })  // Удаляем HTML-теги из текста
                        }

                    )

                    state = state.copy(

                        listProjectsControl = cleanedProjectsControl,

                        listFilteredProjectsControl = cleanedProjectsControl,

                        isVisibilityDeleteComponent = false


                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is ProjectControlIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is ProjectControlIntents.NoDelete -> noDelete()

            is ProjectControlIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)

            }

        }

    }

    fun back(){

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

    }

    fun backFromDataEntry(){

        state = state.copy(

            isVisibilityDataEntryComponent = false,

            updatedItem = null

        )

    }

    fun noDelete(){

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updatedItem = null

        )

    }

    fun openDeleteComponent( item: ServiceModel? ){

        state = state.copy(

            isVisibilityDeleteComponent = true,

            updatedItem = item

        )

    }

    fun openDescription ( index: Int ) {

    val newList = state.listExpendedDescription.toMutableList()

        newList[index] = !state.listExpendedDescription[index]

        state = state.copy(

            listExpendedDescription = newList

        )

    }

    fun inputTextSearchComponent( text: String ) {

        val newList = state.listProjectsControl!!.copy(

            data = state.listProjectsControl!!.data?.filter {

                val companyName = it.project?.name.orEmpty()

                companyName.contains(text, ignoreCase = true)
            }
        )

        state = state.copy(

            listFilteredProjectsControl = newList

        )

        println("Text ${text}")
        println("NewList ${newList}")

    }

}