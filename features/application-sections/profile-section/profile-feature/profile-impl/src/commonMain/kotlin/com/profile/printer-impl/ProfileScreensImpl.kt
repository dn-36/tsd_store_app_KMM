package com.profile.`printer-impl`

import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.ProfileScreensApi
import com.profile.profile.screens.profile_screen.screen.ProfileScreen

class ProfileScreensImpl():ProfileScreensApi {
    override fun profile(): Screen {
       return ProfileScreen
    }

}