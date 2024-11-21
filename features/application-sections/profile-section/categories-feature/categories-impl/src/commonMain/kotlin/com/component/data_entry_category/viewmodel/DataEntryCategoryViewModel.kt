package com.component.data_entry_category.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DataEntryCategoryViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryCategoryState())

    fun processIntents ( intent: DataEntryCategoryIntents){

        when ( intent ) {

            is DataEntryCategoryIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryCategoryIntents.SetScreen -> setScreen(intent.name)

        }

    }

    fun setScreen ( name: String ) {

        if ( state.isSet ) {

            state = state.copy(

                name = name,

                isSet = false

            )
        }

    }

    fun inputTextName ( text: String ) {

        state = state.copy(

            name = text

        )

    }

}