package com.project.chats.screens.dialog.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.fastForEachIndexed
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.dialog.domain.GetListMessagesUseCase
import com.project.chats.screens.dialog.domain.ReadMessegeUseCase
import com.project.chats.screens.dialog.domain.SendMessageUseCase
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.StatusMessage
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.project.core_app.getFormattedDateTime
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.project.nika_screens_chats.history_files_feature.screen.HistoryFilesScreen

class DialogViewModel(
   private val getListMessagesUseCase : GetListMessagesUseCase,
   private val sendMessageUseCase: SendMessageUseCase,
   private val readMessageUseCase: ReadMessegeUseCase
): NetworkViewModel() {
    private var isSeted:Boolean = false
    var state by mutableStateOf(DialogState())

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



             this.launch {
                 while (
                     true
                 ){
                 readMessageUseCase.execute(uiChats)
                 delay(1500L)

                 }
                 }

            while (
               true
            ){
                val listDate = mutableSetOf<String>()

                val listMessages = getListMessagesUseCase.execute(uiChats)?: emptyList()


                listMessages.fastForEachIndexed { i, message ->

                    if(message.isReaded){
                        listMessages[i].statusMessage = StatusMessage.IS_READED
                    }


                    if(!listDate.contains(listMessages[i].time)){
                        listMessages[i].isShowDate = true
                    }
                    listDate.add(message.time)
                }
                if((listMessages.size != 0)  && (state.listMessage.lastOrNull()?.statusMessage?:StatusMessage.IS_SECCUESS)
                    != StatusMessage.IS_ERROR){
                    state = state.copy(
                        listMessage = listMessages
                    )
                }
            if(!isSeted){
               if(listMessages.isNullOrEmpty()){
                        setStatusNetworkScreen(StatusNetworkScreen.ERROR)
                        return@launch
                    }
                delay(200L)
                setStatusNetworkScreen(StatusNetworkScreen.SECCUESS)
                isSeted = true
            }
            delay(1500L)
            }
        }

    }

   fun sendMessageUseCase(text:String,ui:String,scope: CoroutineScope){
       scope.launch(Dispatchers.IO) {


           val list = state.listMessage.toMutableList()
           list.add(
               Message(
                   text,
                   "You",
                   date = getFormattedDateTime().date,
                   time = getFormattedDateTime().time,
                   null,
                   WhoseMessage.YOU,
                   false,
                   statusMessage = StatusMessage.IS_LOADING
               )
           )
           state = state.copy(listMessage = list)

           val result = sendMessageUseCase.execute(text, ui)


           if(result != sendMessageUseCase.ERROR){
               val removeIndex = mutableListOf<Int>()
               val updatedList = list.mapIndexed { index, message ->
                   if(message.statusMessage == StatusMessage.IS_ERROR){
                       removeIndex.add(index)
                   }
                   if (index == list.size - 1) {
                       message.copy(statusMessage = StatusMessage.IS_SECCUESS)
                   } else {
                       message
                   }
               }.toMutableList()

               removeIndex.sortedDescending().forEach {
                   updatedList.removeAt(it)
               }

               state = state.copy(listMessage = updatedList)
           }else{
               val updatedList = list.mapIndexed { index, message ->
                   if (index == list.size - 1) {
                       message.copy(statusMessage = StatusMessage.IS_ERROR)
                   } else {
                       message
                   }
               }
               state = state.copy(listMessage = updatedList)
           }

    }


    }
}