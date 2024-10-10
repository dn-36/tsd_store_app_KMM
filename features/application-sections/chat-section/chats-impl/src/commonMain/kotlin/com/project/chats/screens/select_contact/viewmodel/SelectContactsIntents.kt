package org.example.project.nika_screens_chats.add_chat_feature.viewmodel

import model.Contact


sealed class SelectContactsIntents {

    object Next:SelectContactsIntents()

    object Back:SelectContactsIntents()

    object SetScreen:SelectContactsIntents()

     object ColorButtonAll:SelectContactsIntents()

     object ColorButtonOrganization:SelectContactsIntents()

   data class SelectContact(val selectedContact: Contact):SelectContactsIntents()

    data class CanselContact(val contact: Contact):SelectContactsIntents()

    object ClearingTypedText:SelectContactsIntents()
}