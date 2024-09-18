package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel

object ListItemIntent {
    fun execute(){
        QRcodeMenuViewModel.dimaState = QRcodeMenuViewModel.dimaState.copy(
            alphaListItem = mutableStateOf(true),
            alphaListPrinter = mutableStateOf(false)
        )
    }
}