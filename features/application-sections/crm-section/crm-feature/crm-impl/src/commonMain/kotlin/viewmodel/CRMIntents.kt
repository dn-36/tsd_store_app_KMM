package viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class CRMIntents {

    data class SetScreen (val coroutineScope: CoroutineScope ): CRMIntents()

    data class SelectTypeCRM ( val index: Int ): CRMIntents()

    object Back: CRMIntents()

    object BackToCRMComponent: CRMIntents()

    data class OpenDataEntryComponent ( val coroutineScope: CoroutineScope ): CRMIntents()

}