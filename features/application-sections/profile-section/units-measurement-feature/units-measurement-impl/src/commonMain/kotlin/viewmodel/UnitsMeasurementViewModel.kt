package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ProductsMenuScreenApi
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import domain.usecases.CreateUnitsMeasurementUseCase
import domain.usecases.DeleteUnitMeasurementUseCase
import domain.usecases.GetUnitsMeasurementUseCase
import domain.usecases.UpdateUnitsMeasurementUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import model.UnitResponseModel
import org.koin.mp.KoinPlatform

class UnitsMeasurementViewModel(

    val getUnitsMeasurementUseCase: GetUnitsMeasurementUseCase,

    val createUnitsMeasurementUseCase: CreateUnitsMeasurementUseCase,

    val deleteUnitMeasurementUseCase: DeleteUnitMeasurementUseCase,

    val updateUnitsMeasurementUseCase: UpdateUnitsMeasurementUseCase

): NetworkViewModel () {

    var state by mutableStateOf(UnitsMeasurementState())

    fun processIntents ( intent: UnitsMeasurementIntents ) {

        when (intent) {

            is UnitsMeasurementIntents.Back -> back()

            is UnitsMeasurementIntents.SetScreen -> {

                intent.coroutineScope.launch(Dispatchers.IO) {

                    if (state.isSet) {

                        val listUnits = getUnitsMeasurementUseCase.execute()

                        state = state.copy(

                            listUnitsMeasurement = listUnits,

                            listFilteredUnitsMeasurement = listUnits,

                            isSet = false

                        )
                    }

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is UnitsMeasurementIntents.InputTextSearchComponent -> {

                inputTextSearchComponent(intent.text)

            }

            is UnitsMeasurementIntents.CreateUnitMeasurement -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch (Dispatchers.IO) {

                    createUnitsMeasurementUseCase.execute(intent.name)

                    val listUnits = getUnitsMeasurementUseCase.execute()

                    state = state.copy(

                        listUnitsMeasurement = listUnits,

                        listFilteredUnitsMeasurement = listUnits,

                        isVisibilityDataEntryComponent = false

                    )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is UnitsMeasurementIntents.DeleteUnitMeasurement -> {

                    setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                    intent.coroutineScope.launch(Dispatchers.IO) {

                        deleteUnitMeasurementUseCase.execute(state.updateItem!!.ui)

                        val listUnits = getUnitsMeasurementUseCase.execute()

                        state = state.copy(

                            isVisibilityDeleteComponent = false,

                            updateItem = null,

                            listUnitsMeasurement = listUnits,

                            listFilteredUnitsMeasurement = listUnits,

                            )

                        setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                    }

            }

            is UnitsMeasurementIntents.UpdateUnitMeasurement -> {

                setStatusNetworkScreen(StatusNetworkScreen.LOADING)

                intent.coroutineScope.launch(Dispatchers.IO) {

                    updateUnitsMeasurementUseCase.execute( intent.name, state.updateItem!!.ui )

                    val listCategories = getUnitsMeasurementUseCase.execute()

                    state = state.copy(

                        isVisibilityDataEntryComponent = false,

                        listUnitsMeasurement = listCategories,

                        listFilteredUnitsMeasurement = listCategories,

                        updateItem = null

                        )

                    setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)

                }

            }

            is UnitsMeasurementIntents.LongPressItem -> longPressItem(intent.index)

            is UnitsMeasurementIntents.OpenDeleteComponent -> openDeleteComponent(intent.item)

            is UnitsMeasurementIntents.NoDelete -> noDelete()

            is UnitsMeasurementIntents.OnePressItem -> onePressItem()

            is UnitsMeasurementIntents.BackFromDataEntryUnit -> backFromDataEntryUnit()

            is UnitsMeasurementIntents.OpenCreateDataEntryUnit -> openCreateDataEntryUnit()

            is UnitsMeasurementIntents.OpenUpdateDataEntryUnit -> {

                openUpdateDataEntryUnit(intent.item)

            }

        }
    }

        fun back() {

            val productsMenuScreen: ProductsMenuScreenApi = KoinPlatform.getKoin().get()

            Navigation.navigator.push(productsMenuScreen.productsMenuScreen())

        }


        fun inputTextSearchComponent(text: String) {

            val newList = state.listUnitsMeasurement.filter {

                val name = it.name.orEmpty()

                name.contains(text, ignoreCase = true)

            }

            state = state.copy(

                listFilteredUnitsMeasurement = newList

            )

        }

        fun longPressItem(index: Int) {

            val newList = MutableList(state.listUnitsMeasurement.size) { 0F }

            newList[index] = 1f

            state = state.copy(

                listAlphaTools = newList

            )

        }

    fun backFromDataEntryUnit(  ) {

        state = state.copy(

            isVisibilityDataEntryComponent = false,

            updateItem = null

        )

    }

    fun openCreateDataEntryUnit(  ) {

        state = state.copy(

            isVisibilityDataEntryComponent = true

        )

    }

    fun openUpdateDataEntryUnit( item: UnitResponseModel) {

        state = state.copy(

            isVisibilityDataEntryComponent = true,

            updateItem = item

        )

    }

    fun noDelete() {

        state = state.copy(

            isVisibilityDeleteComponent = false,

            updateItem = null

        )

    }



        fun onePressItem() {

            val newList = MutableList(state.listUnitsMeasurement.size) { 0F }

            state = state.copy(

                listAlphaTools = newList

            )

        }

    fun openDeleteComponent( item: UnitResponseModel) {

        state = state.copy(

            isVisibilityDeleteComponent = true,

            updateItem = item

        )

    }

}