package component.characteristic.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import domain.usecases.CreateCharacteristicUseCase
import domain.usecases.GetCharacteristicsUseCase
import domain.usecases.GetGoodsAndServicesUseCase
import domain.usecases.GetSpecificGoodOrServiceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.CharacteristicModel

class CharacteristicsViewModel (

    val getSpecificGoodOrServiceUseCase: GetSpecificGoodOrServiceUseCase,

    val getCharacteristicsUseCase: GetCharacteristicsUseCase,

    val createCharacteristicUseCase: CreateCharacteristicUseCase

 ): ViewModel() {

    var state by mutableStateOf(CharacteristicsState())

    fun processIntents( intent: CharacteristicsIntents ) {

        when ( intent ) {

            is CharacteristicsIntents.SetScreen -> {

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    if ( state.isSet ) {

                        val listCreatedCharacteristics = intent.updateItem!!.parametrs

                        val listCharacteristic = getCharacteristicsUseCase.execute()

                        state = state.copy(

                            listCharacteristics = listCharacteristic,

                            listFilteredCharacteristics = listCharacteristic,

                            listCreatedParametrs = listCreatedCharacteristics,

                            updateItem = getSpecificGoodOrServiceUseCase.execute(intent.updateItem.ui?:""),

                            isSet = false

                        )

                        withContext(Dispatchers.IO){

                            println("Прверка: ${ state.updateItem }")

                        }

                    }

                }

            }

            is CharacteristicsIntents.CreateCharacteristic -> {

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    createCharacteristicUseCase.execute( state.meaning,

                        state.selectedCharacteristic!!.id?:0,

                        state.updateItem!!.id?:0 )

                    val updateItem = getSpecificGoodOrServiceUseCase.execute(

                        state.updateItem!!.ui?:"")

                    state = state.copy(

                        listCreatedParametrs = updateItem.parametrs,

                        updateItem = updateItem,

                        meaning = "",

                        characteristic = "",

                        listFilteredCharacteristics = getCharacteristicsUseCase.execute(),

                        selectedCharacteristic = null

                    )

                }

            }

            is CharacteristicsIntents.InputTextMeaning -> inputTextMeaning(intent.text)

            is CharacteristicsIntents.InputTextCharacteristic -> inputTextCharacteristic(

                intent.text, intent.list )

            is CharacteristicsIntents.MenuCharacteristics -> menuCharacteristics()

            is CharacteristicsIntents.SelectCharacteristic -> selectCharacteristic(intent.item)

            is CharacteristicsIntents.DeleteSelectedCharacteristic -> deleteSelectedCharacteristic()

            is CharacteristicsIntents.LongPressItem -> longPressItem( intent.index )

            is CharacteristicsIntents.OnePressItem -> onePressItem()

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

    fun longPressItem ( index: Int ) {

        val newList = MutableList(state.listCreatedParametrs.size){0F}

        newList[index] = 1f

        state = state.copy(

            listAlphaTools = newList

        )

    }

    fun onePressItem () {

        val newList = MutableList(state.listCreatedParametrs.size){0F}

        state = state.copy(

            listAlphaTools = newList

        )

    }

}