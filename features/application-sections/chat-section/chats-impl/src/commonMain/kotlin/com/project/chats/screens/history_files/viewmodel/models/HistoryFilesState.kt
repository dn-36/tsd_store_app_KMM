package com.project.chats.screens.history_files.viewmodel.models

data class HistoryFilesState(
    val listPhoto:List<Photo> = listOf(),
    val isVisibilityButtonGoToPlacePhoto:Boolean = false
)