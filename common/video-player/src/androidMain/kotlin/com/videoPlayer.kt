package com

import android.content.pm.ActivityInfo
import android.widget.FrameLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun GoBackToPortraitMode(triggerEffect: Boolean) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity

    LaunchedEffect(triggerEffect) {
        if (triggerEffect) {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }
}

@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    isLandscape: Boolean,
    shouldStop: Boolean,
    onMediaReadyToPlay: () -> Unit
) {
    val activity = LocalContext.current as? ComponentActivity

    if (isLandscape) {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    AndroidView(
        modifier = modifier.fillMaxSize(),
        factory = { context ->
            VideoView(context).apply {
                setVideoPath(url)
                val mediaController = MediaController(context)
                mediaController.setAnchorView(this)
                setMediaController(mediaController)
                setOnPreparedListener {
                    onMediaReadyToPlay()
                    start()

                }
                setOnErrorListener { _, _, _ ->
                    onMediaReadyToPlay()
                    true
                }
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
        },
        update = { view ->
            if (shouldStop) {
                view.stopPlayback()
            } else {
                view.start()
            }
        }
    )
}