package component.disharge.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.ProductGoodsServicesModel

class DischargeViewModel() : ViewModel() {

    var state by mutableStateOf(DischargeState())

    fun processIntents ( intent: DischargeIntents ) {

        when ( intent ) {

            is DischargeIntents.SetScreen -> setScreen(intent.updateItem)

            is DischargeIntents.InputTextManufacturer -> inputTextManufacturer(intent.text)

            is DischargeIntents.InputTextNumberManufacturer -> {

                inputTextNumberManufacturer(intent.text)
            }

            is DischargeIntents.InputTextPostavka -> inputTextPostavka(intent.text)

            is DischargeIntents.IsBu -> isBu()

        }

    }

    fun setScreen( updateItem: ProductGoodsServicesModel? ){

        if ( state.isSet ) {

            var newIsBu = false

            if ( updateItem != null ) {

                if (  updateItem.is_bu != null ) {

                    newIsBu = if (updateItem.is_bu == 0 ) false else true

                }

            }

            state = state.copy(

                manufacturer = if ( updateItem != null ) updateItem.creater?:"" else "",

                numberManufacturer = if ( updateItem != null ) updateItem.nomer_creater?:"" else "",

                postavka = if ( updateItem != null ) updateItem.postavka?:"" else "",

                isBu = newIsBu,

                isSet = false

            )
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
