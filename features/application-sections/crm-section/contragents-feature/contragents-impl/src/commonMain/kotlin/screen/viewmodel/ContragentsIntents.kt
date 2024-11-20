package screen.viewmodel

import kotlinx.coroutines.CoroutineScope
import screen.model.ContragentResponseModel

sealed class ContragentsIntents {

    data class GetContragents ( val coroutineScope: CoroutineScope ): ContragentsIntents()

    data class DeleteContragent ( val coroutineScope: CoroutineScope ): ContragentsIntents()

    data class OpenCreateOrUpdateComponent (val item: ContragentResponseModel?,

                                            val index: Int ): ContragentsIntents()

    data class Create ( val coroutineScope: CoroutineScope, val name: String ): ContragentsIntents()

    data class Update ( val coroutineScope: CoroutineScope, val name: String ): ContragentsIntents()

    object CanselComponent: ContragentsIntents()

    object Back: ContragentsIntents()

    data class OpenDeleteComponent ( val item: ContragentResponseModel ) : ContragentsIntents()

    object NoDelete : ContragentsIntents()

    data class InputTextSearchComponent( val text: String ): ContragentsIntents()


    data class LongPressItem( val index: Int ): ContragentsIntents()

    object OnePressItem: ContragentsIntents()

}