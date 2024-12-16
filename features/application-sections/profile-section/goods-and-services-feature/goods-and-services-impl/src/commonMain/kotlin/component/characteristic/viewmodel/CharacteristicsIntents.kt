package component.characteristic.viewmodel

import kotlinx.coroutines.CoroutineScope
import model.CharacteristicModel
import model.ProductGoodsServicesModel
import viewmodel.GoodsAndServicesIntents

sealed class CharacteristicsIntents {

    data class SetScreen( val coroutineScope: CoroutineScope,

                          val updateItem: ProductGoodsServicesModel?

    ): CharacteristicsIntents()

    data class InputTextMeaning( val text: String ): CharacteristicsIntents()

    data class InputTextCharacteristic( val text: String, val list: List<CharacteristicModel>

    ): CharacteristicsIntents()

    object MenuCharacteristics: CharacteristicsIntents()

    data class SelectCharacteristic ( val item: CharacteristicModel ): CharacteristicsIntents()

    object DeleteSelectedCharacteristic: CharacteristicsIntents()

    data class CreateCharacteristic( val coroutineScope: CoroutineScope): CharacteristicsIntents()

    data class LongPressItem( val index: Int ): CharacteristicsIntents()

    object OnePressItem: CharacteristicsIntents()

}