package com.project.chats.screens.titile_chat.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.select_contact.domain.User
import com.project.chats.screens.titile_chat.domain.CreateChatUseCase
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.core_app.network_base_screen.StatusNetworkScreen
import com.project.network.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class TitleChatViewModel(
    private val createChatUseCase: CreateChatUseCase
):NetworkViewModel() {

    var state by mutableStateOf(TitleGroupState())
   init{
       setStatus(StatusNetworkScreen.SECCUESS)
   }


    fun processIntent(intent: TitleGroupIntents){
        when(intent){
            is TitleGroupIntents.Ready -> {ready(state.text,state.image ,intent.list,intent.scope)}
        }
    }

    fun ready(
        titleChat:String,
        image:ImageBitmap?,
        list:Set<User>,
        scope: CoroutineScope
    ) {
        scope.launch(Dispatchers.IO) {
            setStatus(StatusNetworkScreen.LOADING)
            createChatUseCase.execute(titleChat,image,12,list)
            Navigation.navigator.push(ChatsScreen())
        }


    }
}