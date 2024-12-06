package com.arrival_and_consumption_goods.viewmodel

sealed class ScannerZebraUsbIntents {

    object SetScreen: ScannerZebraUsbIntents()

    data class UpdateScanData( val text: String ): ScannerZebraUsbIntents()

}