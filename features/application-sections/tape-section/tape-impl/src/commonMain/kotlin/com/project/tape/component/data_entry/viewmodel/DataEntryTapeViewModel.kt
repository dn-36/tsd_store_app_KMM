package com.project.tape.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectResponseModel
import file_provider.FileProviderApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform

class DataEntryTapeViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryTapeState())

    fun processIntents ( intent: DataEntryTapeIntents ) {

        when ( intent ) {

            is DataEntryTapeIntents.SetScreen -> setScreen(intent.listProjects,

                intent.listContragents)

            is DataEntryTapeIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryTapeIntents.InputTextDescription -> inputTextDescription(intent.text)

            is DataEntryTapeIntents.InputTextContragent -> inputTextContragent(intent.text,

                intent.list)

            is DataEntryTapeIntents.InputTextProject -> inputTextProject(intent.text,

                intent.list)



            is DataEntryTapeIntents.MenuContragents -> menuContragents()

            is DataEntryTapeIntents.MenuProjects -> menuProjects()


            is DataEntryTapeIntents.SelectContragent -> selectContragent(intent.item)

            is DataEntryTapeIntents.SelectProject -> selectProject(intent.item)


            is DataEntryTapeIntents.DeleteSelectedContragent -> deleteSelectedContragent()

            is DataEntryTapeIntents.DeleteSelectedProject -> deleteSelectedProject()


            is DataEntryTapeIntents.OpenMenuFiles -> openMenuFiles()


            is DataEntryTapeIntents.DeleteSelectedPhoto -> deleteSelectedPhoto()

        }

    }

    fun setScreen( listProjects: List<ProjectResponseModel>,

                   listContragents: List<ContragentsResponseModel> ){

        if ( state.isSet ) {

            state = state.copy(

                listProjects = listProjects,

                filteredListProjects = listProjects,

                listContragents = listContragents,

                filteredListContragents = listContragents,

                isSet = false

            )

        }

    }


    fun inputTextName(text: String) {

        state = state.copy(

            name = text

        )

    }

    fun inputTextDescription(text: String) {

        state = state.copy (

            description = text

        )

    }

    fun inputTextContragent(text: String, listContragents: List<ContragentsResponseModel>) {

        state = state.copy(

            contragent = text,

            filteredListContragents = listContragents

        )

    }

    fun inputTextProject(text: String, listProjects: List<ProjectResponseModel>) {

        state = state.copy (

            project = text,

            filteredListProjects = listProjects

        )

    }



    fun menuContragents() {

     state = state.copy(

         expendedContragents = true,

         expendedProjects = false

     )

    }

    fun menuProjects() {

        state = state.copy(

            expendedContragents = false,

            expendedProjects = true

        )

    }



    fun selectContragent( item: ContragentsResponseModel ) {

     state = state.copy(

         selectedContragent = item,

         expendedContragents = false

     )

    }

    fun selectProject( item: ProjectResponseModel ) {

        state = state.copy(

            selectedProject = item,

            expendedProjects = false

        )

    }


    fun deleteSelectedContragent() {

        state = state.copy(

            selectedContragent = null

        )

    }

    fun deleteSelectedProject() {

        state = state.copy(

            selectedProject = null

        )

    }

    fun openMenuFiles () {

        CoroutineScope(Dispatchers.IO).launch {

            val fileProvider: FileProviderApi = KoinPlatform.getKoin().get()

            fileProvider.pickVideo()
        }

    }

    fun deleteSelectedPhoto() {

        state = state.copy(

            image = null

        )

    }

}