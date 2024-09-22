package org.example.project.presentation.core.models

data class ProductPresentationModel(
    val title:String =  "product TSD store",
    val qrCodeData:String? = null,
    val heightQRcode:Int = 30,
    val fontSize:Int = 12
)