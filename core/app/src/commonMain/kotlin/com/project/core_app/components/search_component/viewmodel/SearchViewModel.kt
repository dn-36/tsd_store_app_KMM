package com.project.core_app.components.search_component.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    var state by mutableStateOf(SearchState())

    fun processIntents ( intent: SearchIntents) {

        when ( intent ) {

            is SearchIntents.InputText -> inputTextName(intent.text)

        }

    }

    fun inputTextName( text: String ) {

        state = state.copy(

            name = text

        )

    }

}