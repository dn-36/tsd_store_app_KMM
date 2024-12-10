package component.disharge.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DischargeViewModel() : ViewModel() {

    var state by mutableStateOf(DischargeState())

    fun processIntents ( intent: DischargeIntents ) {

        when ( intent ) {

            is DischargeIntents.InputTextManufacturer -> inputTextManufacturer(intent.text)

            is DischargeIntents.InputTextNumberManufacturer -> {

                inputTextNumberManufacturer(intent.text)
            }

            is DischargeIntents.InputTextPostavka -> inputTextPostavka(intent.text)

            is DischargeIntents.IsBu -> isBu()

        }

    }

    fun inputTextManufacturer( text: String ) {

        state = state.copy(

            manufacturer = text

        )

    }

    fun inputTextNumberManufacturer( text: String ) {

        state = state.copy(

            numberManufacturer = text

        )

    }

    fun inputTextPostavka( text: String ) {

        state = state.copy(

            postavka = text

        )

    }

    fun isBu( ) {

        state = state.copy(

            isBu = !state.isBu

        )

    }

}
