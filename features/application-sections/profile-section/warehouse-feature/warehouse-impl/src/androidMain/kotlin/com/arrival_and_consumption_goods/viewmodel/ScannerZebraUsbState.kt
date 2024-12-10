package com.arrival_and_consumption_goods.viewmodel

import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.SDKHandler

data class ScannerZebraUsbState (

    val scanData: String = "",

    var sdkHandler: SDKHandler? = null,

    var mScannerInfoList: ArrayList<DCSScannerInfo>  = ArrayList(),

    var  mSNAPIList: ArrayList<DCSScannerInfo> = ArrayList<DCSScannerInfo>(),

    val isSet: Boolean = true,

    val checkSku: Boolean? = null,

    val textNewProduct: String = "",

)
