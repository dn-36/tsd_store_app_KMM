package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.ChoosingItemIntent
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.ChoosingPrinterIntent
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.ListItemIntent
import org.example.project.presentation.feature.qr_code_main.screens.product_search.ui.ProductSearchScreen
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.domain.PrintOnVkpUseCase
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.ListPrinterIntent
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.OpenSettingsIntent

class QRcodeMenuViewModel(
    private val conectUSBUseCase: ConectUSBUseCase,
    private val printerVkpUseCase: PrintOnVkpUseCase
):ViewModel() {
    companion object{
        var dimaState by mutableStateOf(DimaState())
    }
    private var isSetedScreen = false

    fun processIntent(intent: QRcodeMenuIntent){



        when(intent){
            is QRcodeMenuIntent.SetScreen -> {
                if(isSetedScreen) return
                isSetedScreen = true
                NavigatorComponent.navigator = intent.navigator
                conectUSBUseCase.execute()
            }
            is QRcodeMenuIntent.OpenListItem -> {
                ListItemIntent.execute()}
            is QRcodeMenuIntent.OpenListPrinter -> {
                ListPrinterIntent.execute()}
            is QRcodeMenuIntent.OpenSettings -> {
                OpenSettingsIntent.execute()}
            is QRcodeMenuIntent.ChoosingItem -> {
                ChoosingItemIntent.execute(intent.index)}
            is QRcodeMenuIntent.ChoosingPrinter -> {
                ChoosingPrinterIntent.execute(intent.index)}
            is QRcodeMenuIntent.OpenProductSearch -> {
                NavigatorComponent.navigator!!.push(ProductSearchScreen)
            }
            is QRcodeMenuIntent.PrintQRcode -> {
                printerVkpUseCase.execute(
                    "QR code",
                    "Описание",
                    heightQRCodeMM = 20,
                    fontSize = 12F

                )
            }

        }
    }
}