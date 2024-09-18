package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.example.project.presentation.core.Navigator
import org.example.project.presentation.dima_screens.viewmodel.DimaState
import org.example.project.presentation.dima_screens.viewmodel.intents.ChoosingItemIntent
import org.example.project.presentation.dima_screens.viewmodel.intents.ChoosingPrinterIntent
import org.example.project.presentation.dima_screens.viewmodel.intents.ListItemIntent
import org.example.project.presentation.feature.authorization.screens.entering_number.ui.EnteringAnumberScreen
import org.example.project.presentation.feature.qr_code_main.screens.product_list.ui.SearchScreen
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui.QRCodeMenu
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.ListPrinterIntent
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.intents.OpenSettingsIntent

class QRcodeMenuViewModel:ViewModel() {
    companion object{
        var dimaState by mutableStateOf(DimaState())
    }
    fun processIntent(intents: QRcodeMenuIntents){
        when(intents){
            is QRcodeMenuIntents.OpenListItem -> {ListItemIntent.execute()}
            is QRcodeMenuIntents.OpenListPrinter -> {
                ListPrinterIntent.execute()}
            is QRcodeMenuIntents.OpenSettings -> {
                OpenSettingsIntent.execute()}
            is QRcodeMenuIntents.ChoosingItem -> {ChoosingItemIntent.execute(intents.index)}
            is QRcodeMenuIntents.ChoosingPrinter -> {
                ChoosingPrinterIntent.execute(intents.index)}
            is QRcodeMenuIntents.OpenProductSearch -> {
                Navigator.navigator.push(EnteringAnumberScreen)
            }

        }
    }
}