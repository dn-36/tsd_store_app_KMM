package com.project.chats.screens.dialog.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.fastForEachIndexed
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.chats.screens.dialog.domain.SendMessageUseCase
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen

class DialogViewModel(
   private val getListMessagesUseCase : GetListMessagesUseCase,
   private val sendMessageUseCase: SendMessageUseCase
):ViewModel() {
    private var isSeted:Boolean = false
    var dialogState by mutableStateOf(DialogState())

    init {
        println("init DialogViewModel")
    }
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

    fun setScreen(uiChats:String, scope:CoroutineScope){
        scope.launch(Dispatchers.IO){
            if(isSeted){
                return@launch
            }
            isSeted = true
            while (isActive){
                val listDate = mutableSetOf<String>()
                val listMessages =  getListMessagesUseCase.execute(uiChats)
                listMessages.fastForEachIndexed { i, message ->
                    if(!listDate.contains(listMessages[i].date)){
                        listMessages[i].isShowDate = true
                    }
                    listDate.add(message.date)
                }
                if(listMessages.size != 0){
                    dialogState = dialogState.copy(
                        listMessage = listMessages
                    )
                }

                delay(1500L)
            }
        }

    }

   fun sendMessage(text:String,ui:String,scope: CoroutineScope){
       scope.launch(Dispatchers.IO) {
           
           sendMessageUseCase.execute(text, ui, onSeccuess = {

           },
             onError =   {
                 val list = dialogState.listMessage.toMutableList()
                 list.add(
                     Message("error","","","",null,WhoseMessage.YOU,true)
                 )
                   dialogState = dialogState.copy(
                       listMessage = list
                      )
               })

    }


    }
}