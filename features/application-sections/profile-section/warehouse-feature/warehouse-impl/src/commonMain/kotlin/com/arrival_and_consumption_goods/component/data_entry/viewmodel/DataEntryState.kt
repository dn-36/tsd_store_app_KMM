package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.viewmodel

import androidx.compose.ui.graphics.Color
import com.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption

data class DataEntryState(

    val contragentParish: String = "",

    val legalEntityParish: String = "",

    val contragentExpense: String = "",

    val legalEntityExpense: String = "",

    val warehouse: String = "",

    val description: String = "",


    val expandedContragentParish: Boolean = false,

    val expandedLegalEntityParish: Boolean = false,

    val expandedContragentExpense: Boolean = false,

    val expandedLegalEntityExpense: Boolean = false,

    val expandedWarehouse: Boolean = false,


    val selectedContragentParish: ContragentResponseArrivalAndConsumption? = null,

    val selectedContragentExpense: ContragentResponseArrivalAndConsumption? = null,

    val selectedLegalEntityParish: EntityArrivalAndConsumption? = null,

    val selectedLegalEntityExpense: EntityArrivalAndConsumption? = null,

    val selectedWarehouse: WarehouseArrivalAndConsumption? = null,


    var filteredContragentParish: List<ContragentResponseArrivalAndConsumption> = listOf(),

    var filteredContragentExpense: List<ContragentResponseArrivalAndConsumption> = listOf(),

    val filteredWarehouse: List<WarehouseArrivalAndConsumption> = listOf(),

    val isUsed: Boolean = true,


    val listColorBorderTF : List <Color> = listOf ( Color.LightGray, Color.LightGray,

        Color.LightGray, Color.LightGray, Color.LightGray ),

    val onCheck: Boolean = false

)
