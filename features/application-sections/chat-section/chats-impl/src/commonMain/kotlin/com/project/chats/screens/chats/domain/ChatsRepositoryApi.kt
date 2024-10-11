package com.project.chats.screens.chats.domain

interface ChatsRepository {
    fun getListChats():List<String>
}