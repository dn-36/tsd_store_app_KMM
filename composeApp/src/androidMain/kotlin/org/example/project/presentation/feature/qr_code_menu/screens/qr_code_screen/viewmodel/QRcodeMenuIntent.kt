package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import cafe.adriel.voyager.navigator.Navigator


sealed class QRcodeMenuIntent {
    data class SetScreen(
        val titleProduct:String,
        val dataQRcode:String,
        val navigator: Navigator): QRcodeMenuIntent()
    object OpenProductSearch: QRcodeMenuIntent()
    object PrintQRcode: QRcodeMenuIntent()


}