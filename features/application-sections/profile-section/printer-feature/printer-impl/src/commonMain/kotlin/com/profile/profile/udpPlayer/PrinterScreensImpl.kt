package com.profile.profile.udpPlayer

import cafe.adriel.voyager.core.screen.Screen
import com.project.`printer-api`.PrinterScreensApi
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.QRCodeMenuScreen

class PrinterScreensImpl(): PrinterScreensApi {
    override fun printer(): Screen = QRCodeMenuScreen()
}