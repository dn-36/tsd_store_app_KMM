package com.project.tape.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.tape.model.ProjectResponseModel

class DataEntryTapeViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryTapeState())

    fun processIntents ( intent: DataEntryTapeIntents ) {

        when ( intent ) {

            is DataEntryTapeIntents.SetScreen -> setScreen(intent.listProjects)

            is DataEntryTapeIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryTapeIntents.InputTextDescription -> inputTextDescription(intent.text)

        }

    }

    fun setScreen( listProjects: List<ProjectResponseModel> ){

        if ( state.isSet ) {

            state = state.copy(

                listProjects = listProjects,

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

}