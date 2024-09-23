package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.core.models.ProductPresentationModel


sealed class QRcodeMenuIntent {
    data class SetScreen(
        val product:ProductPresentationModel,
        val navigator: Navigator): QRcodeMenuIntent()
    object OpenProductSearch: QRcodeMenuIntent()
    object PrintQRcode: QRcodeMenuIntent()
     object OpenSettingsSizeQRCode: QRcodeMenuIntent()

}