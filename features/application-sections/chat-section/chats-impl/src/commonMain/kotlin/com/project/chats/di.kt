package com.project.chats

import contactProviderModule
import contact_provider.ContactProviderApi
import model.Contact
import org.koin.dsl.module

val chatsModule = module{
     factory{
          ChatScreensImpl() as ChatScreensApi
     }
}