package org.example.project.presentation.core.models

data class ProductPresentationModel(
    val title:String =  "product TSD store",
    val qrCodeData:String? = "product_TSD_store",
    val heightQRcode:Float = 30F,
    val fontSize:Float= 5F
)