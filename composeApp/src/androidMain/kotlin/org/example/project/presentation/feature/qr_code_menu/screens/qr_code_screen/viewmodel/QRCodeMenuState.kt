package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.graphics.Bitmap
import org.example.project.presentation.core.models.ProductPresentationModel
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.koin.mp.KoinPlatform.getKoin

data class QRCodeMenuState(
    val titleProductQRcodeBiteMap: Bitmap? = null,
    val imgBitmap: Bitmap? = null,
    val heightQRcode:Int = 30,
    val fontSize:Int = 10,
    val isOpenedSettings:Boolean = false
)
