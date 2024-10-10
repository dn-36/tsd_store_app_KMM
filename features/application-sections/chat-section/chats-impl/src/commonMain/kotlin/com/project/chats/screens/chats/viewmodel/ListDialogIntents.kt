package org.example.project.nika_screens_chats.list_dialog_feature.viewmodel

sealed class ListDialogIntents {

    object DialogueSelection : ListDialogIntents()

    object AddChat : ListDialogIntents()

}