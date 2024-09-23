package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.content.Context
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.core.models.ProductPresentationModel


sealed class QRcodeMenuIntent {
    data class SetScreen(
        val product:ProductPresentationModel,
        val navigator: Navigator,
        val context:Context
    ): QRcodeMenuIntent()
    object OpenProductSearch: QRcodeMenuIntent()
    data class PrintQRcode(val product: ProductPresentationModel): QRcodeMenuIntent()
     object OpenSettingsSizeQRCode: QRcodeMenuIntent()
     data class ChangeFontSize(val fontSize:Float,val title:String): QRcodeMenuIntent()
     data class ChangeHeightQrCode(val heightQRcode:Float,val dataQRcode:String): QRcodeMenuIntent()
     object SavedSettings:QRcodeMenuIntent()
     object CloseSettings:QRcodeMenuIntent()
     data class СhoicePrinter(val category:CategoryPrinter):QRcodeMenuIntent()


}