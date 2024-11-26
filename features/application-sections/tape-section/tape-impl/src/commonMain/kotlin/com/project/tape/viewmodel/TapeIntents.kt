package com.project.tape.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.CoroutineScope

sealed class TapeIntents {

    data class SetScreen( val coroutineScope: CoroutineScope ): TapeIntents()

    data class OpenCreateDataEntry( val coroutineScope: CoroutineScope ): TapeIntents()

    object BackFromDataEntry: TapeIntents()

    data class CreateElement( val coroutineScope: CoroutineScope, val name: String,

                             val description: String, val idContragent: String,

                              val idProject: String, val image: ImageBitmap?,

                              val video: String ): TapeIntents()

}