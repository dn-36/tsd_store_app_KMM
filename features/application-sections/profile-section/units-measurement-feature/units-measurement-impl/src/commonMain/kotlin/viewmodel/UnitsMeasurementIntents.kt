package viewmodel

import kotlinx.coroutines.CoroutineScope
import model.UnitResponseModel

sealed class UnitsMeasurementIntents {

    object Back: UnitsMeasurementIntents()

    object BackFromDataEntryUnit: UnitsMeasurementIntents()

    object OpenCreateDataEntryUnit: UnitsMeasurementIntents()

    data class OpenUpdateDataEntryUnit( val item: UnitResponseModel): UnitsMeasurementIntents()

    data class SetScreen( val coroutineScope: CoroutineScope ): UnitsMeasurementIntents()

    data class CreateUnitMeasurement( val coroutineScope: CoroutineScope, val name: String

    ): UnitsMeasurementIntents()

    data class UpdateUnitMeasurement( val coroutineScope: CoroutineScope, val name: String

    ): UnitsMeasurementIntents()

    data class DeleteUnitMeasurement( val coroutineScope: CoroutineScope

    ): UnitsMeasurementIntents()

    data class OpenDeleteComponent( val item: UnitResponseModel): UnitsMeasurementIntents()

    object NoDelete: UnitsMeasurementIntents()


    data class InputTextSearchComponent( val text: String ): UnitsMeasurementIntents()

    data class LongPressItem( val index: Int ): UnitsMeasurementIntents()

    object OnePressItem: UnitsMeasurementIntents()

}