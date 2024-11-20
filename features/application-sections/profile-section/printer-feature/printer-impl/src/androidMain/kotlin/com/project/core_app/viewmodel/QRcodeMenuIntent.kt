package com.project.core_app.viewmodel

import android.content.Context
import cafe.adriel.voyager.navigator.Navigator
import com.profile.profile.udpPlayer.core.ProductPresentationModel
import kotlinx.coroutines.CoroutineScope
import com.project.core_app.viewmodel.model.CategoryPrinter


sealed class QRcodeMenuIntent {
    data class SetScreen(
        val product: ProductPresentationModel,
        val navigator: Navigator,
        val context:Context
    ): QRcodeMenuIntent()
     object OpenProductSearch: QRcodeMenuIntent()
     data class PrintQRcode(val product: ProductPresentationModel,val context: Context): QRcodeMenuIntent()
     data class OpenSettingsPrinter(val scope:CoroutineScope): QRcodeMenuIntent()
     data class ChangeFontSize(val fontSize:Float,val title:String): QRcodeMenuIntent()
     data class ChangeHeightQrCode(val heightQRcode:Float,val dataQRcode:String): QRcodeMenuIntent()
     object SavedSettings: QRcodeMenuIntent()
     data class SelectBluetoothDevice (val device: String, val scope:CoroutineScope):
         QRcodeMenuIntent()
     object CloseSettingsVKP: QRcodeMenuIntent()
     data class SearchBluetoothDevice(val scope: CoroutineScope): QRcodeMenuIntent()
     data class СhoicePrinter(val category: CategoryPrinter): QRcodeMenuIntent()
     data class CloseSettingsBluetooth(val scope:CoroutineScope): QRcodeMenuIntent()
     data class OpenSettingsBluetooth(val scope:CoroutineScope): QRcodeMenuIntent()
     data class ChangeSettingsTsc(val x: Int, val y: Int, val height: Int, val weight: Int):
         QRcodeMenuIntent()

}