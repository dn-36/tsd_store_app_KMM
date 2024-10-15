package org.example.project.nika_screens_chats.history_files_feature.viewmodel

import androidx.lifecycle.ViewModel
import com.project.network.Navigation
import com.project.chats.screens.dialog.screen.DialogScreen

class HistoryFilesViewModel : ViewModel() {

    fun processIntent(intent: HistoryFilesIntents) {
        when (intent) {
            is HistoryFilesIntents.Back -> {
                back()
            }
        }
    }

    fun back() {

       // Navigation.navigator.push(DialogScreen())

    }

}