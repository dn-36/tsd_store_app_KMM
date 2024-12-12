package component.characteristic.viewmodel

import model.CharacteristicModel

data class CharacteristicsState(

    val meaning: String = "",

    val characteristic: String = "",

    val listFilteredCharacteristics: List<CharacteristicModel> = emptyList(),

    val selectedCharacteristic: CharacteristicModel? = null,

    val expendedCharacteristics: Boolean = false,

    val isSet: Boolean = true

)
