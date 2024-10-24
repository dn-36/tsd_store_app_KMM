package com.project.chats.screens.chats.viewmodel

import androidx.lifecycle.ViewModel
import com.project.chats.screens.chats.domain.GetListChatsUseCase
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.project.chats.screens.select_contact.screen.SelectContactsScreen
import com.project.chats.screens.dialog.screen.DialogScreen

class ChatsViewModel(
    val getListChatsUseCase: GetListChatsUseCase,
) : ViewModel() {

    private var isSeted = false
    var state = MutableStateFlow(ChatsState())
        private set

    fun processIntent(intent: ChatsIntents) {
        when (intent) {
            is ChatsIntents.DialogueSelection -> {
                dialogueSelection(intent.ui,intent.titleChat,intent.urlIcon,intent.countNewMessage)
            }
            is ChatsIntents.AddChat -> {
                addChat()
            }
            is ChatsIntents.SetScreen -> {
                setScreen(intent.scope)
            }
            is ChatsIntents.ShowDeleteDialog -> {
                deleteDealog()
            }
            is ChatsIntents.CancelDeleteDialog -> {
                cancelDeleteDialog()
            }
        }
    }

    private fun cancelDeleteDialog(){
        state.update {
            it.copy(
                isShowDeleteDialog = false
            )
        }
    }

   private fun dialogueSelection(chatsUi:String,titleChat:String,urlIcon:String?,countNewMessage:Int) {
        //scope.launch(Dispatchers.IO){
            Navigation.navigator.push(DialogScreen(uiChats  = chatsUi,titleChat,urlIcon,countNewMessage))
       // }
    }

   private fun addChat() {
        Navigation.navigator.push(SelectContactsScreen())
    }

   private fun setScreen( scope: CoroutineScope) {
        if (!isSeted) {
            scope.launch(Dispatchers.IO) {
                isSeted = true
                state.update {
                    it.copy(
                      listchats =   getListChatsUseCase.execute()
                    )
                }
            }

        }
    }

   private fun deleteDealog(){
        state.update {
           it.copy(
                isShowDeleteDialog = true
            )
        }
    }

}