package com.project.chats.screens.select_contact.domain



class GetUsersUseCase() {

    fun execute():List<User>{
    return emptyList()
    }
}

data class User(val name:String, val number:String)