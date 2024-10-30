package viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class CRMIntents {

    data class getIncomingCRM ( val coroutineScope: CoroutineScope ): CRMIntents()

}