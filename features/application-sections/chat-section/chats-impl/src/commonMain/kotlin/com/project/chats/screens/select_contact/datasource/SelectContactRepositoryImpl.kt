package com.project.chats.screens.select_contact.datasource

import com.project.chats.screens.select_contact.domain.SelectContactRepositoryApi
import com.project.chats.screens.select_contact.domain.User
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import com.project.network.users_network.UsersApi

class SelectContactRepositoryImpl(
    private val  client: UsersApi,
    private val sharedPrefsApi: SharedPrefsApi
):SelectContactRepositoryApi {

    override fun getContactFromPhone(): List<User> {
       return emptyList()
    }
//now List<UserData>
//dhould be List<UserDomain>
    override suspend fun getContactFromOrganization(): List<User> {
        return client.init(sharedPrefsApi.getToken()).getUsers().map{
            User(it.name?:"",it.phone?:"")
        }

    }
}

