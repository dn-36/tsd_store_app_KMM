package com.project.tape.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class TapeIntents {

    data class SetScreen( val coroutineScope: CoroutineScope ): TapeIntents()

    data class OpenCreateDataEntry( val coroutineScope: CoroutineScope ): TapeIntents()

    object BackFromDataEntry: TapeIntents()

}