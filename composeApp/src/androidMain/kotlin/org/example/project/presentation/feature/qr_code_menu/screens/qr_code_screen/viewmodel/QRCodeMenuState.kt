package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.graphics.Bitmap
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.model.CategoryPrinter
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.model.ConnectionDeviseStatus
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.model.StatusBluetoothLoading

data class QRCodeMenuState(
    val titleProductQRcodeBiteMap: Bitmap? = null,
    val imgBitmap: Bitmap? = null,
    val isLoadingDataOnScreen:Boolean = true,
    val heightQRcode:Float = 20F,
    val fontSize:Float = 10F,
    val categoryPrinter: CategoryPrinter = CategoryPrinter.VKP,
    val isOpenedSettingsVKP:Boolean = false,
    val isOpenedSettingsTSC:Boolean = false,
    val isLoadingConnectionDevice:Boolean = false,
    val deviceTitle:String = "",
    val statusConnected:ConnectionDeviseStatus = ConnectionDeviseStatus.NoShowStatus,
    val statusSearchBluetoothDevice:StatusBluetoothLoading = StatusBluetoothLoading.LOADING,
    val bluetoothDeviceList: List<String> = listOf()
)
