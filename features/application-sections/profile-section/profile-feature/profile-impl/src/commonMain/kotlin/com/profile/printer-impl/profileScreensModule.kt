package com.profile.`printer-impl`

import com.profile.`printer-impl`.ProfileScreensImpl
import com.project.chats.ProfileScreensApi
import org.koin.dsl.module

val profileScreensModule = module {
   factory {
    ProfileScreensImpl() as ProfileScreensApi
   }
}