package org.example.project.presentation.menu_feature.viewmodel

sealed class MenuIntents {
    object Notes: MenuIntents()
    object Contragents: MenuIntents()
    object CRM: MenuIntents()

}