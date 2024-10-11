package org.example.project.nika_screens_chats.list_dialog_feature.viewmodel

import androidx.lifecycle.ViewModel
import com.project.network.Navigation
import org.example.project.nika_screens_chats.add_chat_feature.screen.SelectContactsScreen
import org.example.project.nika_screens_chats.dialog_feature.screen.DialogScreen

import org.koin.mp.KoinPlatform

class ListDialogViewModel : ViewModel() {

    fun processIntent(intent: ListDialogIntents) {
        when (intent) {
            is ListDialogIntents.DialogueSelection -> {
                dialogueSelection()
            }
            is ListDialogIntents.AddChat -> {
                addChat()
            }
        }
    }

    fun dialogueSelection() {

        Navigation.navigator.push(DialogScreen())

    }

    fun addChat() {

        Navigation.navigator.push(SelectContactsScreen())

    }

}