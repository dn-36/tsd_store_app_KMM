package com.profile.profile.screens.ip_camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.settings.SettingsConnectIpCameraScreen
import com.profile.profile.udpPlayer.StreamUdpPlayer
import com.project.core_app.components.menu_bottom_tools.ui.MenuBottomBarTools
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsSection
import com.project.network.Navigation
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.settings

class IpCameraScreen:Screen{

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val player = remember {
            StreamUdpPlayer(
                "236.0.0.1",
                2000,
                20016
            )
        }
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
         //   Column(modifier = Modifier.padding(16.dp),) {
                Text(
                    "Видеонаблюдение",
                    color = Color.Black,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(16.dp)
                    )
           // }
            Image(
                painter = painterResource(Res.drawable.settings),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(30.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        Navigation.navigator.push(SettingsConnectIpCameraScreen())
                    }
            )


            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                MenuBottomBarTools().Content(MenuBottomBarToolsSection.Camera)
            }

            LaunchedEffect(true){
                player.startVideoStream(scope)

            }
            player.VideoStream(Modifier.fillMaxSize().align(Alignment.Center))

        }

        DisposableEffect(Unit) {


            onDispose {
              println("Смерть....")
              player.stopVideoStream()
            }
        }
      player//rtspCamera()
    }
   // player. rtspCamera(getKoin().get())


}