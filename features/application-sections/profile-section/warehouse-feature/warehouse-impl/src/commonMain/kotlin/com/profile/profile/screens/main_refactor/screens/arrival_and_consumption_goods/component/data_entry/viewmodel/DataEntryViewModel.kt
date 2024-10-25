package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption


class DataEntryViewModel: ViewModel() {

  var state by mutableStateOf(DataEntryState())

    fun processIntents(intent: DataEntryIntents) {

        when (intent) {

            is DataEntryIntents.SetScreen -> { setScreen( intent.listContragents,

                intent.listWarehouse, intent.updatedEntityParish,

                intent.updatedEntityExpense, intent.updatedContragentExpense,

                intent.updatedContragentParish, intent.updatedWarehouse) }

            is DataEntryIntents.MenuContragentsExpense -> { menuContragentsExpense() }

            is DataEntryIntents.MenuContragentsParish -> { menuContragentsParish() }

            is DataEntryIntents.MenuWarehouse -> { menuWarehouse() }

            is DataEntryIntents.MenuLegalEntityExpense -> { menuLegalEntityExpense() }

            is DataEntryIntents.MenuLegalEntityParish -> { menuLegalEntityParish() }

            is DataEntryIntents.SelectContragentExpense -> { selectContragentExpense( intent.item ) }

            is DataEntryIntents.SelectContragentParish -> { selectContragentParish( intent.item ) }

            is DataEntryIntents.SelectWarehouse -> { selectWarehouse( intent.item ) }

            is DataEntryIntents.SelectLegalEntityExpense -> { selectLegalEntityExpense( intent.item ) }

            is DataEntryIntents.SelectLegalEntityParish -> { selectLegalEntityParish( intent.item ) }

            is DataEntryIntents.CancelContragentParish -> { canselContragentParish() }

            is DataEntryIntents.CancelContragentExpense -> { canselContragentExpense() }

            is DataEntryIntents.CancelLegalEntityParish -> { canselLegalEntityParish() }

            is DataEntryIntents.CancelLegalEntityExpense -> { canselLegalEntityExpense() }

            is DataEntryIntents.CancelWarehouse -> { canselWarehouse() }

            is DataEntryIntents.TextInputContragentParish -> { inputTextContragentParish ( intent.text, intent.newList ) }

            is DataEntryIntents.TextInputContragentExpense -> { inputTextContragentExpense ( intent.text, intent.newList ) }

            is DataEntryIntents.TextInputLegalEntityParish -> { inputTextLegalEntityParish ( intent.text ) }

            is DataEntryIntents.TextInputLegalEntityExpense -> { inputTextLegalEntityExpense ( intent.text ) }

            is DataEntryIntents.TextInputWarehouse -> { inputTextWarehouse ( intent.text, intent.newList ) }

            is DataEntryIntents.CheckNullTF -> { checkNullTF () }

        }

    }


    fun menuContragentsParish(){

        state = state.copy(

            expandedContragentParish = !state.expandedContragentParish,
            expandedWarehouse = false ,
            expandedContragentExpense = false ,
            expandedLegalEntityExpense = false ,
            expandedLegalEntityParish = false

        )

    }

    fun menuContragentsExpense(){

        state = state.copy(

            expandedContragentParish = false ,
            expandedWarehouse = false ,
            expandedContragentExpense = !state.expandedContragentExpense ,
            expandedLegalEntityExpense = false ,
            expandedLegalEntityParish = false

        )

    }

    fun menuLegalEntityParish(){

        state = state.copy(

            expandedContragentParish = false ,
            expandedWarehouse = false ,
            expandedContragentExpense = false ,
            expandedLegalEntityExpense = false ,
            expandedLegalEntityParish = !state.expandedLegalEntityParish

        )

    }

    fun menuLegalEntityExpense(){

        state = state.copy(

            expandedContragentParish = false ,
            expandedWarehouse = false ,
            expandedContragentExpense = false ,
            expandedLegalEntityExpense = !state.expandedLegalEntityExpense ,
            expandedLegalEntityParish = false

        )

    }

    fun menuWarehouse(){

        state = state.copy(

            expandedContragentParish = false ,
            expandedWarehouse = !state.expandedWarehouse ,
            expandedContragentExpense = false ,
            expandedLegalEntityExpense = false ,
            expandedLegalEntityParish = false

        )

    }

    fun selectContragentParish ( item: ContragentResponseArrivalAndConsumption ) {

        state = state.copy(

            expandedContragentParish = false ,

            selectedContragentParish = item

        )

    }

    fun selectContragentExpense ( item: ContragentResponseArrivalAndConsumption ) {

        state = state.copy(

            expandedContragentExpense = false ,

            selectedContragentExpense = item

        )

    }

    fun selectLegalEntityParish ( item: EntityArrivalAndConsumption ) {

        state = state.copy(

            expandedLegalEntityParish = false ,

            selectedLegalEntityParish = item

        )

    }

    fun selectLegalEntityExpense( item: EntityArrivalAndConsumption ) {

        state = state.copy(

            expandedLegalEntityExpense = false ,

            selectedLegalEntityExpense = item

        )

    }

    fun selectWarehouse ( item: WarehouseArrivalAndConsumption ) {

        state = state.copy(

            expandedWarehouse = false ,

            selectedWarehouse = item

        )

    }

    fun canselContragentParish() {

        state = state.copy(

            selectedContragentParish = null,

            selectedLegalEntityParish = null

        )

    }

    fun canselContragentExpense () {

        state = state.copy(

            selectedContragentExpense = null ,

            selectedLegalEntityExpense = null

        )

    }

    fun canselLegalEntityParish () {

        state = state.copy(

            selectedLegalEntityParish = null

        )

    }

    fun canselLegalEntityExpense () {

        state = state.copy(

            selectedLegalEntityExpense = null

        )

    }

    fun canselWarehouse () {

        state = state.copy(

            selectedWarehouse = null

        )

    }

    fun inputTextContragentParish ( text: String, list: List<ContragentResponseArrivalAndConsumption> ) {

    state = state.copy(

    contragentParish = text,

    filteredContragentParish = list

    )

    }

    fun inputTextContragentExpense ( text: String, list: List<ContragentResponseArrivalAndConsumption> ) {

        state = state.copy(

        contragentExpense = text,

        filteredContragentExpense = list

        )

    }

    fun inputTextLegalEntityParish ( text: String ) {

        state = state.copy(

        legalEntityParish = text

        )

    }

    fun inputTextLegalEntityExpense ( text: String ) {

        state = state.copy(

        legalEntityExpense = text

        )

    }

    fun inputTextWarehouse ( text: String, list: List<WarehouseArrivalAndConsumption> ) {

        state = state.copy(

        warehouse = text,

        filteredWarehouse = list

        )

    }

    fun setScreen ( listContragents: List<ContragentResponseArrivalAndConsumption>,

                    listWarehouse: List<WarehouseArrivalAndConsumption>,

                    updatedContragentExpense : ContragentResponseArrivalAndConsumption?,

                    updatedContragentParish : ContragentResponseArrivalAndConsumption?,

                    updatedEntityExpense : ContragentResponseArrivalAndConsumption?,

                    updatedEntityParish: ContragentResponseArrivalAndConsumption?,

                    updatedWarehouse : WarehouseArrivalAndConsumption? ) {

        if ( state.isUsed ) {

            val selectedContragentParish =  if( updatedContragentParish != null ) updatedContragentParish else null

            val selectedContragentExpense = if( updatedContragentExpense != null ) updatedContragentExpense else null

            val selectedLegalEntityParish = if( updatedEntityParish != null ) updatedEntityParish.entits!![0] else null

            val selectedLegalEntityExpense = if( updatedEntityExpense != null ) updatedEntityExpense.entits!![0] else null

            val selectedWarehouse = if( updatedWarehouse != null ) updatedWarehouse else null

            state = state.copy (

                isUsed = false,

                filteredContragentParish = listContragents,

                filteredContragentExpense = listContragents,

                filteredWarehouse = listWarehouse,

                selectedContragentParish = selectedContragentParish,

                selectedContragentExpense = selectedContragentExpense,

                selectedLegalEntityExpense = selectedLegalEntityExpense,

                selectedLegalEntityParish = selectedLegalEntityParish,

                selectedWarehouse = selectedWarehouse


            )

        }

    }

    fun checkNullTF () {

        val selectedFields = listOf(

            state.selectedContragentParish,

            state.selectedLegalEntityParish,

            state.selectedContragentExpense,

            state.selectedLegalEntityExpense,

            state.selectedWarehouse
        )

        val newList = state.listColorBorderTF.toMutableList()

        var hasNull = true

        // Итерируемся по полям и обновляем цвета, если поле равно null

        selectedFields.forEachIndexed { index, field ->

            if (field == null) {

                newList[index] = Color.Red

                hasNull = false
            }
        }

        // Обновляем состояние

        state = state.copy(
            listColorBorderTF = newList,
            onCheck = hasNull
        )

    }

}