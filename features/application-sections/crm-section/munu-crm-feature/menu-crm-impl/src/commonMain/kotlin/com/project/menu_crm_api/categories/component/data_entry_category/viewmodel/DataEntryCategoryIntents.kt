package com.project.menu_crm_api.categories.component.data_entry_category.viewmodel

sealed class DataEntryCategoryIntents {

    data class InputTextName( val text:String ): DataEntryCategoryIntents()

    data class SetScreen( val name:String ): DataEntryCategoryIntents()

}