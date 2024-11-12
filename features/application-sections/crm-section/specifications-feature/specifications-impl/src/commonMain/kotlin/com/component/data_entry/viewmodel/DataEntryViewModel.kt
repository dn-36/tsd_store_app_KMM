package com.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.CurrencyResponseModel
import com.model.WarehouseModel

 class DataEntryViewModel:ViewModel() {

    var state by mutableStateOf(DataEntryState())

    fun processIntents ( intent: DataEntryIntents ) {

        when ( intent) {

            is DataEntryIntents.SetScreen -> {

                if ( state.isSet ) {

                    state = state.copy(

                        filteredListCurrency = intent.listCurrency,

                        filteredListProducts = intent.listProducts,

                        filteredListWarehouse = intent.listWarehouse,

                        isSet = false

                    )
                }

            }

            is DataEntryIntents.MenuCurrency -> menuCurrency()

            is DataEntryIntents.MenuWarehouse -> menuWarehouse()

            is DataEntryIntents.InputTextCurrency -> inputTextCurrency(intent.text, intent.list)

            is DataEntryIntents.InputTextWarehouse -> inputTextWarehouse(intent.text, intent.list)

            is DataEntryIntents.SelectedWarehouse -> selectedWarehouse(intent.item)

            is DataEntryIntents.SelectedCurrency -> selectedCurrency(intent.item)

            is DataEntryIntents.DeleteSelectedCurrency -> deleteSelectedCurrency()

            is DataEntryIntents.DeleteSelectedWarehouse -> deleteSelectedWarehouse()

        }

    }

    fun menuCurrency () {

        state = state.copy(

            expendedCurrency = !state.expendedCurrency,

            expendedWarehouse = false

        )

    }

    fun menuWarehouse () {

        state = state.copy(

            expendedWarehouse = !state.expendedWarehouse,

            expendedCurrency = false

        )

    }

    fun inputTextCurrency ( text: String,

                            list: List<CurrencyResponseModel> ) {

        state = state.copy(

            currency = text,

            filteredListCurrency = list

        )

    }

    fun inputTextWarehouse ( text: String,

                             list: List<WarehouseModel> ) {

        state = state.copy(

            warehouse = text,

            filteredListWarehouse = list

        )

    }

     fun selectedCurrency ( item: CurrencyResponseModel ) {

         state = state.copy(

             selectedCurrency = item,

             expendedCurrency = false

         )

     }

     fun selectedWarehouse ( item: WarehouseModel ) {

         state = state.copy(

             selectedWarehouse = item,

             expendedWarehouse = false

         )

     }

     fun deleteSelectedCurrency () {

         state = state.copy(

             selectedCurrency = null

         )

     }

     fun deleteSelectedWarehouse () {

         state = state.copy(

             selectedWarehouse = null

         )

     }

}