package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.feature.qr_code_menu.screens.product_search.ui.ProductSearchScreen
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetQRcodeBitmapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnVkpUseCase

class QRcodeMenuViewModel(
    private val conectUSBUseCase: ConectUSBUseCase,
    private val printerVkpUseCase: PrintOnVkpUseCase,
    private val getQRcodeBitmapUseCase: GetQRcodeBitmapUseCase,
    private val getTitleProductUseCase: GetTitleProductBiteMapUseCase
    //private val getTitleProductUseCase: GetTitleProductUseCase
):ViewModel() {

        val state  = MutableStateFlow(QRCodeMenuState())



    private var isSetedScreen = false

    fun processIntent(intent: QRcodeMenuIntent){

        when(intent){
            is QRcodeMenuIntent.SetScreen -> {
                Log.d("test___00-","sdokncoidsnocisdnocdsniocsd")
                if(isSetedScreen) return
                isSetedScreen = true
               val qrCodeBiteMap =  getQRcodeBitmapUseCase
                    .execute(
                        intent.product.qrCodeData?:"",
                        state.value.heightQRcode
                    )
                state.value = state.value.copy(imgBitmap = qrCodeBiteMap)

                state.value = state.value.copy(
                    titleProductQRcodeBiteMap = getTitleProductUseCase.execute(
                       intent.product.title,
                        intent.product.fontSize
                    )
                )
                NavigatorComponent.navigator = intent.navigator
                conectUSBUseCase.execute()

            }

            is QRcodeMenuIntent.OpenProductSearch -> {
                NavigatorComponent.navigator!!.push(ProductSearchScreen)
            }
            is QRcodeMenuIntent.PrintQRcode -> {
                printerVkpUseCase.execute(
                    "QR code",
                    "Описание",
                    heightQRCodeMM = 20,
                    fontSize = 12F

                )
            }

            is QRcodeMenuIntent.OpenSettingsSizeQRCode -> {
                state.value = state.value.copy(
                isOpenedSettings = true
                )
            }
        }
    }
}