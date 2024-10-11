package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel

data class ArrivalAndConsumptionState (

    val counterpartyParish :String = "",
    val expandedCounterpartyParish :Boolean = false,
    val legalEntityParish : String = "",
    val expandedLegalEntityParish : Boolean = false,
    val counterpartyExpense :String = "",
    val expandedCounterpartyExpense :Boolean = false,
    val legalEntityExpense :String = "",
    val expandedLegalEntityExpense :Boolean = false,
    val warehouse :String = "",
    val expandedWarehouse :Boolean = false

    )