package org.example.project.presentation.dima_screens.viewmodel.intents

import androidx.compose.runtime.mutableStateOf
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel

object ChoosingItemIntent {
    fun execute(item:String){
        QRcodeMenuViewModel.dimaState = QRcodeMenuViewModel.dimaState.copy(
            alphaListItem = mutableStateOf(false),
            alphaListPrinter = mutableStateOf(false),
            textItem = mutableStateOf(item)
        )
    }
}