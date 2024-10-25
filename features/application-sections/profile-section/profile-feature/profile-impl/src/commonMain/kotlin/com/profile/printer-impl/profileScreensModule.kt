package com.profile.`printer-impl`

import com.profile.`printer-impl`.ProfileScreensImpl
import com.project.chats.ProfileScreensApi
import org.example.project.presentation.profile_feature.main_feature.viewmodel.ProfileViewModel
import org.koin.dsl.module

val profileScreensModule = module {

   factory { ProfileScreensImpl() as ProfileScreensApi }

   factory { ProfileViewModel() }

}