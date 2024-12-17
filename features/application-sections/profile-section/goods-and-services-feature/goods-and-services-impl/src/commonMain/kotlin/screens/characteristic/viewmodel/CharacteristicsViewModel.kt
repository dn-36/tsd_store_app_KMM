package screens.characteristic.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import domain.usecases.CreateCharacteristicUseCase
import domain.usecases.DeleteCharacteristicUseCase
import domain.usecases.GetCharacteristicsUseCase
import domain.usecases.GetSpecificGoodOrServiceUseCase
import domain.usecases.UpdateCharacteristicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import model.CharacteristicModel

class CharacteristicsViewModel (

    val getSpecificGoodOrServiceUseCase: GetSpecificGoodOrServiceUseCase,

    val getCharacteristicsUseCase: GetCharacteristicsUseCase,

    val createCharacteristicUseCase: CreateCharacteristicUseCase,

    val deleteCharacteristicUseCase: DeleteCharacteristicUseCase,

    val updateCharacteristicUseCase: UpdateCharacteristicUseCase

 ): NetworkViewModel() {

    var state by mutableStateOf(CharacteristicsState())

    fun processIntents( intent: CharacteristicsIntents) {

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

                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CharacteristicsIntents.CreateCharacteristic -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

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

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CharacteristicsIntents.UpdateCharacteristic -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    updateCharacteristicUseCase.execute( state.meaning,

                        state.selectedCharacteristic!!.id?:0,

                        state.updateItem!!.id?:0, state.id )

                    val updateItem = getSpecificGoodOrServiceUseCase.execute(

                        state.updateItem!!.ui?:"")

                    state = state.copy(

                        listCreatedParametrs = updateItem.parametrs,

                        updateItem = updateItem,

                        meaning = "",

                        characteristic = "",

                        listFilteredCharacteristics = getCharacteristicsUseCase.execute(),

                        selectedCharacteristic = null,

                        isUpdate = false,

                        listAlphaTools = emptyList()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

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

            is CharacteristicsIntents.DeleteCharacteristic -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch( Dispatchers.IO ) {

                    deleteCharacteristicUseCase.execute( intent.id )

                    val updateItem = getSpecificGoodOrServiceUseCase.execute(

                        state.updateItem!!.ui?:"")

                    state = state.copy(

                        listCreatedParametrs = updateItem.parametrs,

                        updateItem = updateItem,

                        listAlphaTools = emptyList()

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is CharacteristicsIntents.ClickUpdateCharacteristic -> clickUpdateCharacteristic(intent.index)

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

    fun clickUpdateCharacteristic( index: Int ) {

        state = state.copy(

            meaning = state.listCreatedParametrs[index].name ?:"0",

            selectedCharacteristic = state.listCharacteristics.find{

                it.id == state.listCreatedParametrs[index].parametr?.id },

            isUpdate = true,

            id = state.listCreatedParametrs[index].id?:0

        )

    }

}