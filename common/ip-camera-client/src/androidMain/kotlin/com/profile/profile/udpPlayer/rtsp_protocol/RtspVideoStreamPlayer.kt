package com.profile.profile.udpPlayer.rtsp_protocol

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout


actual class  RtspVideoStreamPlayer {
    companion object {
        lateinit var context: Context
    }

    @Composable
    actual fun Content(
        rtspUrl: String,
        modifier: Modifier
    ) {
        val context = LocalContext.current
        val libVLC = remember {
            LibVLC(context, arrayListOf("--file-caching=2000", "--rtsp-tcp"))
        }
        val mediaPlayer = remember { MediaPlayer(libVLC) }
        val isAttached = remember { mutableStateOf(false) }

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
    }}
