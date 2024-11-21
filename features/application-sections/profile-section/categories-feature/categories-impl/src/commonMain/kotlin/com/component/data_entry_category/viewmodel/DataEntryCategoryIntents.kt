package com.component.data_entry_category.viewmodel

sealed class DataEntryCategoryIntents {

    data class InputTextName( val text:String ): DataEntryCategoryIntents()

    data class SetScreen( val name:String ): DataEntryCategoryIntents()

}