package component.data_entry_units.viewmodel

sealed class DataEntryUnitIntents {

    data class InputTextName( val name: String ): DataEntryUnitIntents()

    data class SetScreen( val name:String ): DataEntryUnitIntents()

}