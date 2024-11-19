package com.project.core_app.components.search_component.viewmodel

sealed class SearchIntents {

    data class InputText( val text: String ): SearchIntents()

}