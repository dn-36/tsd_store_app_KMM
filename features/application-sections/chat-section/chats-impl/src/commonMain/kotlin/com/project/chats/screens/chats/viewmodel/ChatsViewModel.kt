package com.project.chats.screens.chats.viewmodel

import androidx.lifecycle.ViewModel
import com.project.chats.screens.chats.domain.GetListChatsUseCase
import com.project.network.Navigation
import com.project.network.chats_network.ChatsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.nika_screens_chats.add_chat_feature.screen.SelectContactsScreen
import com.project.chats.screens.dialog.screen.DialogScreen

class ChatsViewModel(
    val getListChatsUseCase: GetListChatsUseCase
) : ViewModel() {

    private var isSeted = false
    var state = MutableStateFlow(ChatsState())
        private set

    fun processIntent(intent: ChatsIntents) {
        when (intent) {
            is ChatsIntents.DialogueSelection -> {
                dialogueSelection(intent.userId,intent.scope)
            }
            is ChatsIntents.AddChat -> {
                addChat()
            }
            is ChatsIntents.SetScreen -> {
                setScreen(intent.scope)
            }
        }
    }

    fun dialogueSelection(chatsUi:String, coroutineScope: CoroutineScope) {
        coroutineScope.launch(Dispatchers.IO){
            println("--------qqqqq-----------")
            println("qqqqq: ${ChatsApi().getListMassengers(chatsUi)}")
            println("--------qqqqq-----------")
            Navigation.navigator.push(DialogScreen(chatsUi))
        }
    }

    fun addChat() {
        Navigation.navigator.push(SelectContactsScreen())
    }

    fun setScreen( scope: CoroutineScope) {
        if (!isSeted) {
            scope.launch(Dispatchers.IO) {
                isSeted = true
                state.update {
                    it.copy(
                        getListChatsUseCase.execute()
                    )
                }
            }

        }
    }
}