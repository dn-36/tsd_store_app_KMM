package com.project.chats

import org.koin.dsl.module

val chatsModule = module{
     factory{
          ChatScreensImpl() as ChatScreensApi
     }
}