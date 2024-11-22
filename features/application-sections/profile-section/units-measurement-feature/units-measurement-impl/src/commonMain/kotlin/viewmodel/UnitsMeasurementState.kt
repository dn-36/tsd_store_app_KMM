package viewmodel

import model.UnitResponseModel

data class UnitsMeasurementState(

    val listUnitsMeasurement: List<UnitResponseModel> = emptyList(),

    val listFilteredUnitsMeasurement: List<UnitResponseModel> = emptyList(),

    val listAlphaTools: List<Float> = emptyList(),

    val isSet: Boolean = true,

    val isVisibilityDataEntryComponent: Boolean = false,

    val isVisibilityDeleteComponent: Boolean = false,

    val updateItem: UnitResponseModel? = null,

)
