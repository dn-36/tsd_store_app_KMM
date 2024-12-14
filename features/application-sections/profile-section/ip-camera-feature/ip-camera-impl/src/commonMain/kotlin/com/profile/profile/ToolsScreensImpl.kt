package com.profile.profile

import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.ip_camera.IpCameraScreen
import com.project.chats.IPCamearaScreensApi

class ToolsScreensImpl():IPCamearaScreensApi {
    override fun ipCamera(): Screen {
       return IpCameraScreen()
    }

}