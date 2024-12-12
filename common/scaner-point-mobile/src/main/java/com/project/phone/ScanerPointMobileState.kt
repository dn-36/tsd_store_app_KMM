package com.project.phone

import android.os.Build


data class ScanerPointMobileState(
    val barType: String = "bar_type",
    val scanResult: String = "scan_result",
    val autoScanEnabled: Boolean = false,
    val beepEnabled: Boolean = false,
    val eventEnabled: Boolean = true,
    val showEnableDialog: Boolean = false,
    val isPMModel: Boolean = Build.MODEL.startsWith("PM")
)
