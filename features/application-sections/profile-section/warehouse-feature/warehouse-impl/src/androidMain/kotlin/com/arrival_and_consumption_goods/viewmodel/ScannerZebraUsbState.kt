package com.arrival_and_consumption_goods.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.SDKHandler

data class ScannerZebraUsbState(

    val scanData: String = "",

    var sdkHandler: SDKHandler? = null,

    var mScannerInfoList: ArrayList<DCSScannerInfo>  = ArrayList(),

    var  mSNAPIList: ArrayList<DCSScannerInfo> = ArrayList<DCSScannerInfo>(),

    val isSet: Boolean = true,

    var number: Int = 0

)
