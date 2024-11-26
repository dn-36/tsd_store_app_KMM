package com.project.chats.screens.dialog.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import com.project.chats.screens.chats.viewmodel.ChatsIntents
import com.project.chats.screens.dialog.components.DialogComponentScreen
import kotlinx.coroutines.CoroutineScope

sealed class DialogIntents {
    object Back : DialogIntents()
    data class HistoryFiles(val dialogComponentScreen: DialogComponentScreen) : DialogIntents()
    object OpenSelectFileComponent : DialogIntents()
    object CloseSelectFileComponent : DialogIntents()

    data class AddNewPhotosGalleryOrCamera ( val images: List<ImageBitmap> ): DialogIntents()
    data class DeleteSelectedPhoto ( val image: ImageBitmap ): DialogIntents()

    data class SetScreen(

        val uiChats:String,

        val scope:CoroutineScope

    ) : DialogIntents()
}