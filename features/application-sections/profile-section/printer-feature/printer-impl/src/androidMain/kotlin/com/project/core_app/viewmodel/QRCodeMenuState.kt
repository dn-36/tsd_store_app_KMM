package com.project.core_app.viewmodel

import android.graphics.Bitmap
import com.project.core_app.viewmodel.model.CategoryPrinter
import com.project.core_app.viewmodel.model.ConnectionDeviseStatus
import com.project.core_app.viewmodel.model.StatusBluetoothLoading
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.TypeQrCode

data class QRCodeMenuState(
    val titleProductQRcodeBiteMap: Bitmap? = null,
    val imgBitmap: Bitmap? = null,
    val isLoadingDataOnScreen:Boolean = true,
    val heightQRcode:Float = 20F,
    val fontSize:Float = 5F,
    val categoryPrinter: CategoryPrinter = CategoryPrinter.VKP,
    val isOpenedSettingsVKP:Boolean = false,
    val isOpenedBluetoothSettingsTSC:Boolean = false,
    val isLoadingConnectionDevice:Boolean = false,
    val isOpenTicketSettingsTSC:Boolean = false,
    val deviceTitle:String = "",
    val barCodeWidth:Float = 1F,
    val statusConnected: ConnectionDeviseStatus = ConnectionDeviseStatus.NoShowStatus,
    val statusSearchBluetoothDevice: StatusBluetoothLoading = StatusBluetoothLoading.LOADING,
    val bluetoothDeviceList: List<String> = listOf(),
    val x: Float = 42F,
    val y: Float = 90F,
    val heightTicketTsc: Int = 60,
    val weightTicketTsc: Int = 60,
    val qrCodeDataText:String = "",
    val typeQrCode: TypeQrCode = TypeQrCode.BAR_CODE
)
