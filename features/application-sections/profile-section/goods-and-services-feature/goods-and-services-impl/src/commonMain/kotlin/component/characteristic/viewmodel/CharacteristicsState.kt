package component.characteristic.viewmodel

import model.CharacteristicModel
import model.ParametrModel
import model.ProductGoodsServicesModel

data class CharacteristicsState(

    val meaning: String = "",

    val characteristic: String = "",

    val updateItem: ProductGoodsServicesModel? = null,

    val listFilteredCharacteristics: List<CharacteristicModel> = emptyList(),

    val listCharacteristics: List<CharacteristicModel> = emptyList(),

    val listCreatedParametrs: List<ParametrModel> = emptyList(),

    val selectedCharacteristic: CharacteristicModel? = null,

    val expendedCharacteristics: Boolean = false,

    val isSet: Boolean = true,

    val listAlphaTools: List<Float> = emptyList()

)
