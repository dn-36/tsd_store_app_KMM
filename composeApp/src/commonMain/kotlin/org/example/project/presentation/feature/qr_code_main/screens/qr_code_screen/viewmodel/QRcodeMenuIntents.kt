package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel

sealed class QRcodeMenuIntents {
    object OpenListPrinter: QRcodeMenuIntents()
    object OpenListItem: QRcodeMenuIntents()
    object OpenSettings: QRcodeMenuIntents()
    data class ChoosingItem(val index:String): QRcodeMenuIntents()
    data class ChoosingPrinter(val index:String): QRcodeMenuIntents()
    object OpenProductSearch:QRcodeMenuIntents()
}