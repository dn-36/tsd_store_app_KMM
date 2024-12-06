package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.scanner_zebra_usb_component

import UsbScannerApi

data class ScannerZebraUsbState(

    val isSet: Boolean = true,

    val barcodeData: String = "",

    val scannerZebraUsb: UsbScannerApi? = null

)
