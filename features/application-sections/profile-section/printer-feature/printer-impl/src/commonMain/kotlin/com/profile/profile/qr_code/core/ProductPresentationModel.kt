package com.profile.profile.qr_code.core

data class ProductPresentationModel(
    val title:String =  "product TSD store",
    val qrCodeData:String? = "product_TSD_store",
    val heightQRcode:Float = 30F,
    val fontSize:Float= 10F
)