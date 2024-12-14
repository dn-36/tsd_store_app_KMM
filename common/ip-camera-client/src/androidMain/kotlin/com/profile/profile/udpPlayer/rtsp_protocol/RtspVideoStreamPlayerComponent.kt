package com.profile.profile.udpPlayer.rtsp_protocol

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.launch
import org.videolan.libvlc.util.VLCVideoLayout
import android.os.Environment
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.mp.KoinPlatform.getKoin
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import java.io.File




actual class  RtspVideoStreamPlayerComponent {
    companion object {
        lateinit var context: Context
    }

    val viewnodel = RtspVideoStreamPlayerViewModel()


    init {
        viewnodel.initialize(getKoin().get())
    }
    @Composable
    actual fun Content(
        rtspUrl: String,
        modifier: Modifier
    ) {

        val scope = rememberCoroutineScope()
         context = LocalContext.current

        LaunchedEffect(true) {
            viewnodel.startStream("rtsp://192.168.1.150:2000/unicast")
            }


        DisposableEffect(true) {
            onDispose {
                scope.launch {
                    viewnodel.stopStream()
                    viewnodel.stopRecording()
                }


            }
        }

        // Отображение видео с фоном
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
           // if(viewnodel.isSecuessConect.value) {
                AndroidView(
                    factory = { context ->
                        VLCVideoLayout(context).apply {
                            post {
                                if (!viewnodel.isAttached.value) {
                                    viewnodel.mediaPlayer?.attachViews(this, null, false, false)
                                    viewnodel.isAttached.value = true


                                }
                            }
                        }
                    },
                    update = { videoLayout ->
                        if (!viewnodel.isAttached.value) {
                            viewnodel.mediaPlayer?.attachViews(videoLayout, null, false, false)
                            viewnodel.isAttached.value = true

                        }
                    }
                )

           // }else{
           Column( modifier = Modifier
               .alpha(if(viewnodel.isSecuessConect.value)0F else 1F)
               .align(Alignment.Center)){
               Text(
                   "Cоединения...",
                   modifier = Modifier
                       .align(Alignment.CenterHorizontally),
                   color = Color.White,
                   fontSize = 25.sp
               )
               CircularProgressIndicator(
                   modifier = Modifier
                       .size(30.dp)
                       .align(Alignment.CenterHorizontally),
                   color = Color.Blue,

               )
           }

        }


      //  }
    }



    actual suspend fun startRecording(url: String) = viewnodel.startRecording(url)

    actual suspend fun startStream(url: String) = viewnodel.startStream(url)

    actual suspend fun stopStream() = viewnodel.stopStream()

    actual suspend fun stopRecordStream()  = viewnodel.stopRecording()


}



