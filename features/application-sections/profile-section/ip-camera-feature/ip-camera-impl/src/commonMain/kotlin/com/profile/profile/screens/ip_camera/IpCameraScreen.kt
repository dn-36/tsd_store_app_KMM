package com.profile.profile.screens.ip_camera

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.ip_camera.viewmodel.IpCameraViewModel
import com.profile.profile.screens.settings.SettingsConnectIpCameraScreen
import com.profile.profile.udpPlayer.rtsp_protocol.RtspVideoStreamPlayerComponent
import com.project.network.Navigation
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.back_white
import project.core.resources.record
import project.core.resources.settings
import project.core.resources.settings_wrhite
import project.core.resources.stop_record
import kotlin.jvm.Transient

class IpCameraScreen:Screen{

   @Transient private val viewModel:IpCameraViewModel = getKoin().get()


    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()

        LaunchedEffect(true){
       // viewModel.clickRecord(scope)
        }

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){

           Text(
           "Видеонаблюдение",
            color = Color.Black,
            fontSize = 26.sp,
            modifier = Modifier.padding(16.dp)
            )

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

              viewModel.IpCameraView()

                Image(
                    painter = painterResource(Res.drawable.back_white),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(30.dp)
                        .align(Alignment.TopStart)
                        .clickable {
                        viewModel.back(scope)
                    }
                    )
            Image(
                painter = painterResource(Res.drawable.settings_wrhite),
                contentDescription = null,
                modifier = Modifier
                    .padding(15.dp)
                    .size(30.dp)
                    .align(Alignment.TopEnd)
                    .clickable {
                        viewModel.goToSetting()
                    }
            )

            Row(modifier = Modifier
                .padding(20.dp)
                .align(Alignment.BottomEnd)
                .height(40.dp)//.width(100.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray)
                .clickable { viewModel.clickRecord(scope) },
                horizontalArrangement = Arrangement.End
                ){
                Text(
                    "${if(viewModel.state.value.isRecorded) viewModel.state.value.stopwatch else ""} Запись",
                    color = Color.White,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterVertically)
                    )
                Image(
                    painter = painterResource(
                        if(!viewModel.state.value.isRecorded)
                        Res.drawable.record
                        else
                        Res.drawable.stop_record
                    ),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp).size(20.dp)
                        .align(Alignment.CenterVertically),


                )
            }
        }

        DisposableEffect(Unit) {

            onDispose {
              println("Смерть....")

            }
        }

    }



}