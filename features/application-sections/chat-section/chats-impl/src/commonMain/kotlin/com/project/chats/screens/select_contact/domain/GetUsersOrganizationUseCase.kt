package com.project.chats.screens.select_contact.domain



class GetUsersOrganizationUseCase(private val selectContactRepositoryApi: SelectContactRepositoryApi) {

    suspend fun execute():List<User>{
    return selectContactRepositoryApi.getContactFromOrganization()
    }
}

