package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.udpPlayer.core.ProductPresentationModel

actual class QRCodeMenuScreen actual constructor() : Screen {
    actual var product: ProductPresentationModel = ProductPresentationModel()

    @Composable
    override fun Content() {

    }
}