package viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class CRMIntents {

    data class getIncomingCRM ( val coroutineScope: CoroutineScope ): CRMIntents()
    data class SelectTypeCRM ( val index: Int ): CRMIntents()

    object Back: CRMIntents()

}