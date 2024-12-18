package com.project.chats.screens.chats.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.project.chats.screens.chats.domain.DeleteChatUseCase
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
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import kotlinx.coroutines.delay
import org.koin.core.component.getScopeId

class ChatsViewModel(
    val getListChatsUseCase: GetListChatsUseCase,
    val deleteChat : DeleteChatUseCase
) : NetworkViewModel() {

    private var isSeted = false
    var state = MutableStateFlow(ChatsState())
        private set
    private lateinit var selectedUiChat:String

    fun processIntent(intent: ChatsIntents) {
        when (intent) {
            is ChatsIntents.DialogueSelection -> {
                dialogueSelection(intent.ui,intent.titleChat,intent.urlIcon)
            }
            is ChatsIntents.AddChat -> {
                addChat()
            }
            is ChatsIntents.SetScreen -> {
                setScreen(intent.scope)
            }
            is ChatsIntents.ShowDeleteDialog -> {
                println("&&&&&&&&&&& "+intent.uiChat)
                showDeleteDealog(intent.uiChat)
            }
            is ChatsIntents.CancelDeleteDialog -> {
                cancelDeleteDialog()
            }
            is ChatsIntents.DeleteChat -> {

                deleteChat(selectedUiChat ,intent.scope)
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

    private fun deleteChat(ui:String,scope: CoroutineScope){
        scope.launch {
            try {
                setStatusNetworkScreen(StatusNetworkScreen.LOADING)
                deleteChat.execute(ui)
                println("+++++++++-----" + deleteChat.execute(ui))
                state.update {
                    it.copy(
                        isShowDeleteDialog = false,
                        listchats = getListChatsUseCase.execute()
                    )
                }
                setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
            } catch (e: Exception) {
                setStatusNetworkScreen(StatusNetworkScreen.ERROR)
            }

        }
    }



   private fun dialogueSelection(chatsUi:String,titleChat:String,urlIcon:String?) {
            Navigation.navigator.push(
                DialogScreen(
                    chatsUi,
                    titleChat,
                    urlIcon,

                  //  countNewMessage
                )
            )
    }

   private fun addChat() {
        Navigation.navigator.push(SelectContactsScreen())
    }

   private fun setScreen( scope: CoroutineScope) {
       if (!isSeted) {
               scope.launch(Dispatchers.IO) {
                   while (true) {
                       try {
                          val listChats =  getListChatsUseCase.execute()
                           if(listChats.size == 0){
                               continue
                           }
                           state.update {
                               it.copy(
                                   listchats = listChats
                               )
                           }
                           if (!isSeted) {
                               isSeted = true
                               setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                           }
                       }catch (e:Exception){

                      }
                       delay(1500L)
                   }

               }



       }
   }

   private fun showDeleteDealog(ui:String){
       selectedUiChat = ui
        state.update {
           it.copy(
                isShowDeleteDialog = true,
            )
        }
    }


}