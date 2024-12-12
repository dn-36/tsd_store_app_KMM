package com.project.phone

import kotlinx.coroutines.CoroutineScope

sealed class MainEvent {
//ScanerPointMobileEvent
    object ToggleAutoScan : MainEvent()
    object ScanOn : MainEvent()
    object ScanOff : MainEvent()
    object DismissDialog : MainEvent()
    data class EnableScannerClicked(val scope:CoroutineScope) : MainEvent()

    // События от системы/сканера
    data class OnScanResult(val barType: String, val result: String) : MainEvent()
    data class OnScannerStateChanged(val state: Int) : MainEvent()
}