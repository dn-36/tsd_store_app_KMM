package com.project.chats.screens.history_files.viewmodel

import androidx.lifecycle.ViewModel
import com.project.network.Navigation
import org.example.project.nika_screens_chats.history_files_feature.viewmodel.HistoryFilesIntents

class HistoryFilesViewModel : ViewModel() {

    fun processIntent(intent: HistoryFilesIntents) {
        when (intent) {
            is HistoryFilesIntents.Back -> {
                back()
            }
        }
    }

    fun back() {

        Navigation.navigator.pop()

    }

}