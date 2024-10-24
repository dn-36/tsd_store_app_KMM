package com.project.chats.screens.select_contact.domain

interface SelectContactRepositoryApi {

    fun getContactFromPhone():List<User>
  suspend  fun getContactFromOrganization():List<User>

}