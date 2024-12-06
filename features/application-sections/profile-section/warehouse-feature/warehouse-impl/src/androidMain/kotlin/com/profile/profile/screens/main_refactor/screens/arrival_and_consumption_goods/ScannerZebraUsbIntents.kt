package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods

sealed class ScannerZebraUsbIntents {

    object SetScreen: ScannerZebraUsbIntents()

    data class UpdateScanData( val text: String ): ScannerZebraUsbIntents()

}