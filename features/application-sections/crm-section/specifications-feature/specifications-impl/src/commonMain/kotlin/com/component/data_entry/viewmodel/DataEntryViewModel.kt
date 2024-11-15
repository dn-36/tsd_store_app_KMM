package com.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.SpecificResponseModel
import com.model.WarehouseModel

 class DataEntryViewModel:ViewModel() {

    var state by mutableStateOf(DataEntryState())

    fun processIntents ( intent: DataEntryIntents ) {

        when ( intent) {

            is DataEntryIntents.SetScreen -> setScreen(intent.listWarehouse,intent.listProducts,

                intent.listCurrency,intent.selectedCurrency,intent.selectedWarehouse,intent.selectedStatus,

                intent.selectedName)

            is DataEntryIntents.MenuCurrency -> menuCurrency()

            is DataEntryIntents.MenuWarehouse -> menuWarehouse()

            is DataEntryIntents.MenuStatus -> menuStatus()

            is DataEntryIntents.InputTextCurrency -> inputTextCurrency(intent.text, intent.list)

            is DataEntryIntents.InputTextWarehouse -> inputTextWarehouse(intent.text, intent.list)

            is DataEntryIntents.InputTextName -> inputTextName(intent.text)

            is DataEntryIntents.SelectedWarehouse -> selectedWarehouse(intent.item)

            is DataEntryIntents.SelectedCurrency -> selectedCurrency(intent.item)

            is DataEntryIntents.SelectedStatus -> selectedStatus(intent.name,intent.index)

            is DataEntryIntents.DeleteSelectedCurrency -> deleteSelectedCurrency()

            is DataEntryIntents.DeleteSelectedWarehouse -> deleteSelectedWarehouse()

            is DataEntryIntents.DeleteSelectedStatus -> deleteSelectedStatus()

        }

    }

     fun setScreen (

                     listWarehouse: List<WarehouseModel>,

                    listProducts: List<ProductResponseModel>,

                    listCurrency: List<CurrencyResponseModel>,

                     selectedCurrency: CurrencyResponseModel?,

                     selectedWarehouse: WarehouseModel?,

                     selectedStatus: Pair<String,Int>?,

                     selectedName: String, ) {

         if ( state.isSet ) {

             state = state.copy(

                 filteredListCurrency = listCurrency,

                 filteredListProducts = listProducts,

                 filteredListWarehouse = listWarehouse,

                 selectedWarehouse = selectedWarehouse,

                 selectedCurrency = selectedCurrency,

                 selectedStatus = selectedStatus,

                 name = selectedName,

                 isSet = false

             )
         }

     }

    fun menuCurrency () {

        state = state.copy(

            expendedCurrency = !state.expendedCurrency,

            expendedWarehouse = false,

            expendedStatus = false

        )

    }

    fun menuWarehouse () {

        state = state.copy(

            expendedWarehouse = !state.expendedWarehouse,

            expendedCurrency = false,

            expendedStatus = false

        )

    }

     fun menuStatus () {

         state = state.copy(

             expendedStatus = !state.expendedStatus,

             expendedCurrency = false,

             expendedWarehouse = false

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

     fun inputTextName ( text: String ) {

         state = state.copy(

             name = text

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

     fun selectedStatus ( name: String, index: Int ) {

         state = state.copy(

             selectedStatus = Pair(name,index),

             expendedStatus = false

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

     fun deleteSelectedStatus () {

         state = state.copy(

             selectedStatus = null

         )

     }

}