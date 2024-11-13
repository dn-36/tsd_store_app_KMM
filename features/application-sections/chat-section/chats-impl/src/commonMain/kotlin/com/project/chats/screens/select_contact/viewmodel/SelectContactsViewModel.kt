package com.project.chats.screens.select_contact.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.project.chats.screens.chats.screen.ChatsScreen
import com.project.chats.screens.select_contact.domain.GetUsersOrganizationUseCase
import com.project.chats.screens.select_contact.domain.User
import com.project.core_app.network_base_screen.NetworkViewModel
import com.project.network.Navigation
import contact_provider.ContactProviderApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import com.project.chats.screens.titile_chat.screen.TitleChatScreen
import org.koin.mp.KoinPlatform

class SelectContactsViewModel(
    private val getUsersUseCase: GetUsersOrganizationUseCase
):NetworkViewModel() {

    var state by mutableStateOf(SelectContactsState())
    private var setedScreen = false
    fun processIntent(intent: SelectContactsIntents){

        when(intent){

            is SelectContactsIntents.Next -> {next()}

            is SelectContactsIntents.Back -> {back()}

            is SelectContactsIntents.SetScreen -> {
                if(!setedScreen) {
                    setedScreen = true
                    showContactsOrganization(intent.scope)
                }
            }

            is SelectContactsIntents.ColorButtonAll -> {colorButtonAll(intent.scope)}

            is SelectContactsIntents.ColorButtonOrganization -> {colorButtonOrganization(intent.scope)}

            is SelectContactsIntents.SelectContact -> { selectContact(intent.selectedContact)}

            is SelectContactsIntents.CanselContact -> {cancelContact(intent.contact)}

            is SelectContactsIntents.ClearingTypedText -> {clearingTypingText()}
        }
    }

    fun next() {
      println("LLLLL\n\n"+state.selectedListContacts+"\n\nLLLL")
        Navigation.navigator.push(TitleChatScreen(state.selectedListContacts))

    }

    fun back() {

        Navigation.navigator.push(ChatsScreen())

    }

    fun colorButtonAll(scope: CoroutineScope){

          showContactsOrganization(scope)

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

    fun selectContact(selectedContact: User){

        var newList = state.selectedListContacts.toMutableList()

        newList.add(selectedContact)

        state = state.copy(
            selectedListContacts = newList.toSet()
        )

    }

    fun cancelContact(contact:User){

        var newList = state.selectedListContacts.toMutableList()

       newList.remove(contact)

        state = state.copy(
            selectedListContacts = newList.toSet()
        )

    }

  private  fun colorButtonOrganization(scope: CoroutineScope){
        scope.launch(Dispatchers.IO) {
            state = state.copy(
                colorButtonAll = Color.LightGray,
                colorButtonOrganization = Color(0xFF60BCE6),
                listContacts = getUsersUseCase.execute()
            )
        }

    }

   private fun showContactsOrganization(scope: CoroutineScope) {

       val contactProvider: ContactProviderApi = KoinPlatform.getKoin().get()

       scope.launch {

           state = state.copy(
               listContacts = contactProvider.getAllContacts().map {
                   User(it.name, it.phoneNumber)
               }
           )
       }


        }
    }


