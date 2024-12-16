package component.characteristic.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import model.CharacteristicModel
import model.ProductGoodsServicesModel

class CharacteristicsViewModel: ViewModel() {

    var state by mutableStateOf(CharacteristicsState())

    fun processIntents( intent: CharacteristicsIntents ) {

        when ( intent ) {

            is CharacteristicsIntents.SetScreen -> setScreen( intent.listCharacteristics,

                intent.updateItem)

            is CharacteristicsIntents.InputTextMeaning -> inputTextMeaning(intent.text)

            is CharacteristicsIntents.InputTextCharacteristic -> inputTextCharacteristic(

                intent.text, intent.list )

            is CharacteristicsIntents.MenuCharacteristics -> menuCharacteristics()

            is CharacteristicsIntents.SelectCharacteristic -> selectCharacteristic(intent.item)

            is CharacteristicsIntents.DeleteSelectedCharacteristic -> deleteSelectedCharacteristic()

        }

    }

    fun setScreen( listCharacteristics: List<CharacteristicModel>,

                   updateItem: ProductGoodsServicesModel? ) {

        if ( state.isSet ) {

            state = state.copy(

                listFilteredCharacteristics = listCharacteristics,

                isSet = false

            )

        }

    }

    fun inputTextMeaning(text: String) {

        state = state.copy(

            meaning = text

        )

    }

    fun inputTextCharacteristic(text: String, list: List<CharacteristicModel>) {

        state = state.copy(

            characteristic = text,

            listFilteredCharacteristics = list

        )

    }

    fun selectCharacteristic( item: CharacteristicModel ){

        state = state.copy(

            selectedCharacteristic = item,

            expendedCharacteristics = false

        )

    }

    fun deleteSelectedCharacteristic(){

        state = state.copy(

            selectedCharacteristic = null

        )

    }

    fun menuCharacteristics(){

        state = state.copy(

            expendedCharacteristics = !state.expendedCharacteristics

        )

    }

}