package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.bluetooth.BluetoothDevice
import android.graphics.Bitmap

data class QRCodeMenuState(
    val titleProductQRcodeBiteMap: Bitmap? = null,
    val imgBitmap: Bitmap? = null,
    val heightQRcode:Float = 20F,
    val fontSize:Float = 10F,
    val categoryPrinter:CategoryPrinter = CategoryPrinter.VKP,
    val isOpenedSettingsVKP:Boolean = false,
    val isOpenedSettingsTSC:Boolean = false,
    val bluetoothDeviceList: List<String> = listOf()
)
