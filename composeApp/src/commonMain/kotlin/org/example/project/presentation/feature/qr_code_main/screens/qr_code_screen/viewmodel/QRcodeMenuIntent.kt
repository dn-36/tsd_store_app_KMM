package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel

import cafe.adriel.voyager.navigator.Navigator


sealed class QRcodeMenuIntent {
    object OpenListPrinter: QRcodeMenuIntent()
    object OpenListItem: QRcodeMenuIntent()
    data class SetScreen(val navigator: Navigator):QRcodeMenuIntent()
    object OpenSettings: QRcodeMenuIntent()
    data class ChoosingItem(val index:String): QRcodeMenuIntent()
    data class ChoosingPrinter(val index:String): QRcodeMenuIntent()
    object OpenProductSearch:QRcodeMenuIntent()
    object PrintQRcode:QRcodeMenuIntent()


}