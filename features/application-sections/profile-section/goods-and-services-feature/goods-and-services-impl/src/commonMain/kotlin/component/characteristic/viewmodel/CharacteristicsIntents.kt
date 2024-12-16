package component.characteristic.viewmodel

import model.CharacteristicModel
import model.ProductGoodsServicesModel

sealed class CharacteristicsIntents {

    data class SetScreen( val listCharacteristics: List<CharacteristicModel>,

                          val updateItem: ProductGoodsServicesModel?

    ): CharacteristicsIntents()

    data class InputTextMeaning( val text: String ): CharacteristicsIntents()

    data class InputTextCharacteristic( val text: String, val list: List<CharacteristicModel>

    ): CharacteristicsIntents()

    object MenuCharacteristics: CharacteristicsIntents()

    data class SelectCharacteristic ( val item: CharacteristicModel ): CharacteristicsIntents()

    object DeleteSelectedCharacteristic: CharacteristicsIntents()

}