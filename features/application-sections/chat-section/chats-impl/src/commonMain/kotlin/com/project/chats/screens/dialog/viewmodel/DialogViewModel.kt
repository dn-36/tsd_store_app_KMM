package com.project.chats.screens.dialog.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.chats.screen.ChatsScreen
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen

class DialogViewModel(
   private val getListMessagesUseCase : GetListMessagesUseCase
):ViewModel() {

    var dialogState by mutableStateOf(DialogState())
    private set

    fun processIntent(intent: DialogIntents){
        when(intent){
            is DialogIntents.Back -> {back()}
            is DialogIntents.HistoryFiles -> {historyFiles()}
            is DialogIntents.SetScreen -> {
                setScreen(intent.uiChats,intent.scope)
            }
        }
    }

   private fun back() {

        Navigation.navigator.push(ChatsScreen())

    }

   private fun historyFiles() {

        Navigation.navigator.push(HistoryFilesScreen())

    }

    private fun setScreen(uiChats:String, scope:CoroutineScope){
        scope.launch(Dispatchers.IO){
            dialogState = dialogState.copy(
                listMessage = getListMessagesUseCase.execute(uiChats)
            )
        }

    }
}