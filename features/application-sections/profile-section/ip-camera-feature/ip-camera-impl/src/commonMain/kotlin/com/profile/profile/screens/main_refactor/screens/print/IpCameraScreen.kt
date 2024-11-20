package com.profile.profile.screens.main_refactor.screens.print

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.udpPlayer.StreamUdpPlayer
import com.project.core_app.components.menu_bottom_tools.ui.MenuBottomBarTools
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsSection

class IpCameraScreen:Screen{

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Видеонаблюдение", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                MenuBottomBarTools().Content(MenuBottomBarToolsSection.Camera)
            }

            val player =   StreamUdpPlayer (
                "236.0.0.1",
                2000,
                20016
            )

            player.startVideoStream(scope)
            player.VideoStream(Modifier.fillMaxSize().align(Alignment.Center))
        }
    }
}