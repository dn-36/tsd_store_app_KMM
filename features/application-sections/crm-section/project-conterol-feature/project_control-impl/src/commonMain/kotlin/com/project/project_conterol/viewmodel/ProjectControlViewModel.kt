package org.example.project.presentation.project_control.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.network.Navigation
import com.project.network.notes_network.model.removeHtmlTags
import com.project.project_conterol.domain.usecases.GetProjectsControlUseCase
import com.project.project_conterol.domain.usecases.GetProjectsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class ProjectControlViewModel (

    val getProjectsControlUseCase: GetProjectsControlUseCase,

    val getProjectsUseCase: GetProjectsUseCase

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

        }

    }

    fun back(){

        val menuScreen: MenuCrmScreenApi = KoinPlatform.getKoin().get()

        Navigation.navigator.push( menuScreen.MenuCrm() )

    }

    fun openDescription ( index: Int ) {

    val newList = state.listExpendedDescription.toMutableList()

        newList[index] = !state.listExpendedDescription[index]

        state = state.copy(

            listExpendedDescription = newList

        )

    }

}