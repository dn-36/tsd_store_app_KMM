package component.data_entry_units.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DataEntryUnitViewModel: ViewModel() {

    var state by mutableStateOf(DataEntryUnitState())

    fun processIntents ( intent: DataEntryUnitIntents ) {

        when (intent) {

        is DataEntryUnitIntents.InputTextName -> inputTextName(intent.name)

        is DataEntryUnitIntents.SetScreen -> setScreen(intent.name)

        }

    }

    fun inputTextName( text: String ) {

        state = state.copy(

            name = text

        )

    }

    fun setScreen ( name: String ) {

        if ( state.isSet ) {

            state = state.copy(

                name = name,

                isSet = false

            )
        }

    }

}