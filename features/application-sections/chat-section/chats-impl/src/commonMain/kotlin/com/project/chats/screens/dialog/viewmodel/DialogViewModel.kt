package org.example.project.nika_screens_chats.dialog_feature.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.chats.screen.ChatsScreen
import com.project.network.Navigation
import org.example.project.nika_screens_chats.dialog_feature.screen.DialogScreen
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen
import org.example.project.presentation.nika_screens_chats.dialog_feature.viewmodel.DialogState

class DialogViewModel:ViewModel() {

    var dialogState by mutableStateOf(DialogState())

    fun processIntent(intent: DialogIntents){
        when(intent){
            is DialogIntents.Back -> {back()}
            is DialogIntents.HistoryFiles -> {historyFiles()}
        }
    }

    fun back() {

        Navigation.navigator.push(ChatsScreen())

    }

    fun historyFiles() {

        Navigation.navigator.push(HistoryFilesScreen())

    }
}