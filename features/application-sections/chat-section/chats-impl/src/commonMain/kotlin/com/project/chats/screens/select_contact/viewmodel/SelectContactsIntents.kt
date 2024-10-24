package com.project.chats.screens.select_contact.viewmodel

import com.project.chats.screens.select_contact.domain.User
import kotlinx.coroutines.CoroutineScope


sealed class SelectContactsIntents {

    object Next: SelectContactsIntents()

    object Back: SelectContactsIntents()

    data class SetScreen( val scope: CoroutineScope): SelectContactsIntents()

    data class ColorButtonAll(val scope: CoroutineScope): SelectContactsIntents()

    data class  ColorButtonOrganization(val scope: CoroutineScope): SelectContactsIntents()

   data class SelectContact(val selectedContact: User): SelectContactsIntents()

    data class CanselContact(val contact: User): SelectContactsIntents()

    object ClearingTypedText: SelectContactsIntents()
}