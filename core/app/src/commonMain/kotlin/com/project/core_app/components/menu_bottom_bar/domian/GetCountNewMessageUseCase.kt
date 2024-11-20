package com.project.core_app.components.menu_bottom_bar.domian

class GetCountNewMessageUseCase(private val repositoryImpl : MenuBottomBarRepositoryApi) {
    suspend fun execute():Int {
        var newMessage = 0
        val listChatsNewMessage = repositoryImpl.getAllChatCountNewMessage(
            repositoryImpl.getToken()
        )
        listChatsNewMessage.forEach {
            if(it != 0){
                newMessage += it
            }
        }
        return newMessage
    }

}
