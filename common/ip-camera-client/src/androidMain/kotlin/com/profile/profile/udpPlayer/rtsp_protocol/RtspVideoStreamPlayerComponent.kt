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
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import org.koin.mp.KoinPlatform.getKoin
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import java.io.File

/*
actual class RtspVideoStreamPlayerComponent {

    private lateinit var libVLC:LibVLC
    private lateinit var mediaPlayer: MediaPlayer
    private var rtspUrl:String = ""

    companion object {
        lateinit var context: Context
    }

    actual fun init (
        rtspUrl:String
    ) {
        this.rtspUrl = rtspUrl
        context = getKoin().get()
        libVLC = LibVLC(context, arrayListOf("--file-caching=2000", "--rtsp-tcp"))
        mediaPlayer = MediaPlayer(libVLC)
        try {
            val media = Media(libVLC, Uri.parse(rtspUrl)).apply {
                setHWDecoderEnabled(true, false)
                addOption(":network-caching=10")
                addOption(":clock-jitter=0")
                addOption(":clock-synchro=0")
                // addOption(":skip-frames")
                //  addOption(":drop-late-frames")
            }
            mediaPlayer.media = media
            media.release()
            mediaPlayer.play()
        } catch (e: Exception) {
            Log.e("VideoPlayer", "Failed to start playback: ${e.localizedMessage}")
        }
    }



    @Composable
    actual fun Content(
        modifier: Modifier
    ) {

        val isAttached = remember { mutableStateOf(false) }

        // Освобождение ресурсов
        DisposableEffect(true) {
            onDispose {
                mediaPlayer.stop()
                mediaPlayer.detachViews()
                mediaPlayer.release()
                libVLC.release()
            }
        }

        // Отображение видео с фоном
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AndroidView(
                factory = { context ->
                    VLCVideoLayout(context).apply {
                        post {
                            if (!isAttached.value) {
                                mediaPlayer.attachViews(this, null, false, false)
                                isAttached.value = true
                            }
                        }
                    }
                },
                update = { videoLayout ->
                    if (!isAttached.value) {
                        mediaPlayer.attachViews(videoLayout, null, false, false)
                        isAttached.value = true
                    }
                }
            )
        }
    }
}
*/


actual class  RtspVideoStreamPlayerComponent {
    companion object {
        lateinit var context: Context
    }

    val viewnodel by lazy {
        RtspVideoStreamPlayerViewModel()
    }

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

        LaunchedEffect(Unit) {
           // viewnodel.initialize(context)
            viewnodel.startStream("rtsp://192.168.1.150:2000/unicast")
            }
          // viewnodel.startStream("rtsp://192.168.1.150:2000/unicast")
       // }

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
                .background(Color.White)
        ) {
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
        }
    }



    actual suspend fun startRecording(url: String) = viewnodel.startRecording(url)

    actual suspend fun startStream(url: String) = viewnodel.startStream(url)

    actual suspend fun stopStream() = viewnodel.stopStream()

    actual suspend fun stopRecordStream()  = viewnodel.stopRecording()


}


//////////////////______{{{{{{{{{{{{{{{{{{________}}}}}}}}}}}}}}}}}}}}}}______/////////////////////////



