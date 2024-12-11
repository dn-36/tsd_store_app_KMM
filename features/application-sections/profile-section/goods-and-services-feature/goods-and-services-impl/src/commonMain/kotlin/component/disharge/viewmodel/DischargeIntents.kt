package component.disharge.viewmodel

import model.ProductGoodsServicesModel

sealed class DischargeIntents {

    data class SetScreen( val updateItem: ProductGoodsServicesModel? ): DischargeIntents()

    data class InputTextManufacturer( val text: String ): DischargeIntents()

    data class InputTextNumberManufacturer( val text: String ): DischargeIntents()

    data class InputTextPostavka( val text: String ): DischargeIntents()

    object IsBu: DischargeIntents()

}