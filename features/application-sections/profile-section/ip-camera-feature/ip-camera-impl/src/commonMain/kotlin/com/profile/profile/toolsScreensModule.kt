package com.profile.profile

import com.project.chats.IPCamearaScreensApi
import org.koin.dsl.module

val toolsScreensModule = module {

   factory { ToolsScreensImpl() as IPCamearaScreensApi }

}