package com

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun GoBackToPortraitMode(triggerEffect: Boolean)

@Composable
expect fun VideoPlayer(
    modifier: Modifier,
    url: String,
    isLandscape: Boolean = false,
    shouldStop: Boolean = false,
    onMediaReadyToPlay: () -> Unit
)