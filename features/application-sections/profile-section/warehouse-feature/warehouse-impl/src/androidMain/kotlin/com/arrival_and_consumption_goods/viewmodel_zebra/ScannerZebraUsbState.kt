package com.arrival_and_consumption_goods.viewmodel_zebra

import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.SDKHandler

data class ScannerZebraUsbState (

    val scanData: String = "",

    var sdkHandler: SDKHandler? = null,

    var mScannerInfoList: ArrayList<DCSScannerInfo>  = ArrayList(),

    var mSNAPIList: ArrayList<DCSScannerInfo> = ArrayList<DCSScannerInfo>(),

    val checkSku: Boolean? = null,

    val textNewProduct: String = "",

    val connectedScannerId: Int? = null

)
