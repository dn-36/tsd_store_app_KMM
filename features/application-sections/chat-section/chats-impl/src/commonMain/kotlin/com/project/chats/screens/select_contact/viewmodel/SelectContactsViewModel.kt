package com.project.chats.screens.select_contact.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.network.Navigation
import contact_provider.ContactProviderApi
import model.Contact
import org.example.project.nika_screens_chats.add_chat_feature.viewmodel.SelectContactsIntents

import org.example.project.nika_screens_chats.title_group_feature.screen.TitleGroupScreen
import org.example.project.presentation.nika_screens_chats.add_chat_feature.select_contacts_screen.viewmodel.SelectContactsState
import org.koin.mp.KoinPlatform

class SelectContactsViewModel:NetworkViewModel() {

    var state by mutableStateOf(SelectContactsState())

    fun processIntent(intent: SelectContactsIntents){

        when(intent){

            is SelectContactsIntents.Next -> {next()}

            is SelectContactsIntents.Back -> {back()}

            is SelectContactsIntents.SetScreen -> {setScreen()}

            is SelectContactsIntents.ColorButtonAll -> {colorButtonAll()}

            is SelectContactsIntents.ColorButtonOrganization -> {colorButtonOrganization()}

            is SelectContactsIntents.SelectContact -> {selectContact(intent.selectedContact)}

            is SelectContactsIntents.CanselContact -> {cancelContact(intent.contact)}

            is SelectContactsIntents.ClearingTypedText -> {clearingTypingText()}
        }
    }

    fun next() {

        Navigation.navigator.push(TitleGroupScreen())

    }

    fun back() {

        Navigation.navigator.push(ChatsScreen())

    }

    fun colorButtonAll(){

        state = state.copy(
            colorButtonAll = Color(0xFF60BCE6),
            colorButtonOrganization = Color.LightGray
        )

    }

    fun clearingTypingText(){

        state = state.copy(
            text = ""
        )

    }

    fun selectContact(selectedContact: Contact){

        var newList = state.listSelectedContacts.toMutableList()

        newList.add(selectedContact)

        state = state.copy(
            listSelectedContacts = newList
        )

    }

    fun cancelContact(contact:Contact){

        var newList = state.listSelectedContacts.toMutableList()

       newList.remove(contact)

        state = state.copy(
            listSelectedContacts = newList
        )

    }

    fun colorButtonOrganization(){

        state = state.copy(
            colorButtonAll = Color.LightGray,
            colorButtonOrganization = Color(0xFF60BCE6)
        )

    }

    fun setScreen(){

        if(state.isUsed.value) {

            state.isUsed.value = false

            val contactProvider: ContactProviderApi = KoinPlatform.getKoin().get()



            println("2")
            println("2")
            println("${contactProvider.getAllContacts()}")
            println("2")
            println("2")

            state = state.copy(

               listContactsPhone = contactProvider.getAllContacts(),

                filteredContacts = contactProvider.getAllContacts()

            )
        }
    }
}