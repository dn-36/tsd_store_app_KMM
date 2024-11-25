@file:OptIn(ExperimentalForeignApi::class)

package com

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.*
import platform.AVKit.*
import platform.Foundation.*
import platform.Foundation.NSURL
import androidx.compose.runtime.DisposableEffect
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCAction
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemStatusReadyToPlay
import platform.AVFoundation.AVURLAsset
import platform.UIKit.UIView
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.CoreMedia.CMTimeMake
import platform.UIKit.UIColor
import platform.UIKit.NSLayoutConstraint
import platform.Foundation.NSKeyValueObservingOptionNew
import platform.darwin.NSObject

@Composable
actual fun GoBackToPortraitMode(triggerEffect: Boolean) {
    if (triggerEffect) {

        //OrientationHelper.setOrientationToPortrait()

    }
}

// Класс-наблюдатель для обработки изменений статуса AVPlayerItem
class PlayerObserver(
    private val onStatusReady: () -> Unit
) : NSObject() {

    // Переопределяем метод, чтобы отслеживать изменения статуса AVPlayerItem
    fun observeValueForKeyPath(
        keyPath: String?,
        ofObject: Any?,
        change: Map<Any?, *>?,
        context: CPointer<*>?
    ) {
        when (keyPath) {
            "status" -> {
                if (ofObject is AVPlayerItem && ofObject.status == AVPlayerItemStatusReadyToPlay) {
                    onStatusReady() // Call callback when ready
                }
            }
            else -> {
                // Handle other key paths or log an error
                println("Unhandled key path: $keyPath")
            }
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    modifier: Modifier,
    url: String,
    isLandscape: Boolean,
    shouldStop: Boolean,
    onMediaReadyToPlay: () -> Unit
) {
    val nsUrl = NSURL(string = url)
    requireNotNull(nsUrl) { "Invalid URL: $url" }

    val playerItem = AVPlayerItem(asset = AVURLAsset(uRL = nsUrl, options = null))
    val player = AVPlayer(playerItem = playerItem)
    val avPlayerViewController = AVPlayerViewController().apply {
        this.player = player
        showsPlaybackControls = true
    }

    // Убедитесь, что AVPlayerViewController будет использоваться для автоматического контроля воспроизведения.
    avPlayerViewController.player?.play()

    // Вставьте AVPlayerViewController в UI
    UIKitView(
        modifier = modifier,
        factory = {
            val containerView = UIView().apply {
                backgroundColor = UIColor.blackColor
            }

            val playerView = avPlayerViewController.view!!
            playerView.translatesAutoresizingMaskIntoConstraints = false
            containerView.addSubview(playerView)

            NSLayoutConstraint.activateConstraints(
                listOf(
                    playerView.topAnchor.constraintEqualToAnchor(containerView.topAnchor),
                    playerView.bottomAnchor.constraintEqualToAnchor(containerView.bottomAnchor),
                    playerView.leadingAnchor.constraintEqualToAnchor(containerView.leadingAnchor),
                    playerView.trailingAnchor.constraintEqualToAnchor(containerView.trailingAnchor)
                )
            )

            containerView
        },
        update = {
            if (shouldStop) {
                avPlayerViewController.player?.pause()
                avPlayerViewController.player?.seekToTime(CMTimeMake(value = 0, timescale = 1))
            } else {
                avPlayerViewController.player?.play()
            }
        }
    )
}
/*@OptIn(ExperimentalForeignApi::class)
fun CMTime.convertToCValue(): CValue<CMTime> {
    // Используем встроенную функцию из CoreMedia для создания структуры CMTime
    return CMTimeMakeWithEpoch(value, timescale, epoch)
}*/

