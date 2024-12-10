package component.disharge.viewmodel

sealed class DischargeIntents {

    data class InputTextManufacturer( val text: String ): DischargeIntents()

    data class InputTextNumberManufacturer( val text: String ): DischargeIntents()

    data class InputTextPostavka( val text: String ): DischargeIntents()

    object IsBu: DischargeIntents()

}