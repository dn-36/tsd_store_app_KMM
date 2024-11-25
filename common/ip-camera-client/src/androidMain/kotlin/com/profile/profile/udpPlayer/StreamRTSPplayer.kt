package com.profile.profile.udpPlayer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.SurfaceTexture
import android.net.Uri
import android.util.Log
import android.view.Surface
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import com.vladpen.StreamData
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.io.IOException
import org.koin.mp.KoinPlatform.getKoin
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout


class  VideoPlayerScreen() {
    companion object {
        lateinit var context: Context
    }

    @Composable
    fun Content(
     //   streamId: Int,
        modifier: Modifier = Modifier
    ) {
        val context = LocalContext.current
        val libVLC = remember {
            LibVLC(context, arrayListOf("--file-caching=2000", "--rtsp-tcp"))
        }
        val mediaPlayer = remember { MediaPlayer(libVLC) }
        val isAttached = remember { mutableStateOf(false) }

        // Инициализация RTSP-ссылки
        val rtspUrl = "rtsp://192.168.1.150:2000/unicast"

        // Инициализация потока
        LaunchedEffect(Unit) {
            try {
                val media = Media(libVLC, Uri.parse(rtspUrl)).apply {
                    setHWDecoderEnabled(true, false)
                    addOption(":network-caching=150")
                    addOption(":clock-jitter=0")
                    addOption(":clock-synchro=0")
                }
                mediaPlayer.media = media
                media.release()
                mediaPlayer.play()
            } catch (e: Exception) {
                Log.e("VideoPlayer", "Failed to start playback: ${e.localizedMessage}")
            }
        }

        // Освобождение ресурсов
        DisposableEffect(Unit) {
            onDispose {
                mediaPlayer.stop()
                mediaPlayer.detachViews()
                mediaPlayer.release()
                libVLC.release()
            }
        }

        // Отображение видео
        AndroidView(
            modifier = modifier,
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