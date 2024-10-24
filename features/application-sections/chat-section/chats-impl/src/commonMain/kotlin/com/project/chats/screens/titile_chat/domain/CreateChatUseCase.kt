package com.project.chats.screens.titile_chat.domain

import androidx.compose.ui.graphics.ImageBitmap
import com.project.chats.screens.select_contact.domain.User
import com.project.core_app.utils.imageBitmapToBase64

class CreateChatUseCase(private val repository:TitleChatRepositoryApi){

   suspend fun execute(titleChat:String,image:ImageBitmap?,projectId:Int,list:Set<User>){

            repository.creatChat(
                titleChat,
                if(image == null) null else imageBitmapToBase64(image),
                projectId,
                list.toList(),
                repository.getToken()
            )

    }
}



