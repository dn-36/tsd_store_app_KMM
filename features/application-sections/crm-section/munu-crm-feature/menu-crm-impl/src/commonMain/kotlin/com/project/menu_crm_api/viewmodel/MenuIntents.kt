package com.project.menu_crm_api.viewmodel

sealed class MenuIntents {
    object Notes: MenuIntents()
    object Contragents: MenuIntents()
    object CRM: MenuIntents()
    object Specifications: MenuIntents()
    object Locations: MenuIntents()

}