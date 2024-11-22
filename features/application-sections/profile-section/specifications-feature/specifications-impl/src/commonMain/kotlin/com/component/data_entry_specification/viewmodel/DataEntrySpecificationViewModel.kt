package com.component.data_entry_specification.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.WarehouseModel

 class DataEntrySpecificationViewModel:ViewModel() {

    var state by mutableStateOf(DataEntrySpecificationState())

    fun processIntents ( intent: DataEntrySpecificationIntents ) {

        when ( intent) {

            is DataEntrySpecificationIntents.SetScreen -> setScreen(intent.listWarehouse,intent.listProducts,

                intent.listCurrency,intent.selectedCurrency,intent.selectedWarehouse,intent.selectedStatus,

                intent.selectedName)

            is DataEntrySpecificationIntents.MenuCurrency -> menuCurrency()

            is DataEntrySpecificationIntents.MenuWarehouse -> menuWarehouse()

            is DataEntrySpecificationIntents.MenuStatus -> menuStatus()

            is DataEntrySpecificationIntents.InputTextCurrency -> inputTextCurrency(intent.text, intent.list)

            is DataEntrySpecificationIntents.InputTextWarehouse -> inputTextWarehouse(intent.text, intent.list)

            is DataEntrySpecificationIntents.InputTextName -> inputTextName(intent.text)

            is DataEntrySpecificationIntents.SelectedWarehouse -> selectedWarehouse(intent.item)

            is DataEntrySpecificationIntents.SelectedCurrency -> selectedCurrency(intent.item)

            is DataEntrySpecificationIntents.SelectedStatus -> selectedStatus(intent.name,intent.index)

            is DataEntrySpecificationIntents.DeleteSelectedCurrency -> deleteSelectedCurrency()

            is DataEntrySpecificationIntents.DeleteSelectedWarehouse -> deleteSelectedWarehouse()

            is DataEntrySpecificationIntents.DeleteSelectedStatus -> deleteSelectedStatus()

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