package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
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

    val state = MutableStateFlow(QRCodeMenuState())


    private var isSetedScreen = false

    fun processIntent(intent: QRcodeMenuIntent) {

        when (intent) {
            is QRcodeMenuIntent.SetScreen -> {
                Log.d("test___00-", "sdokncoidsnocisdnocdsniocsd")
                if (isSetedScreen) return
                isSetedScreen = true
                val qrCodeBiteMap = getQRcodeBitmapUseCase
                    .execute(
                        intent.product.qrCodeData ?: "",
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
                    heightQRCodeMM = 20F,
                    fontSize = 12F

                )
            }

            is QRcodeMenuIntent.OpenSettingsSizeQRCode -> {
                state.value = state.value.copy(
                    isOpenedSettings = true
                )
            }

            is QRcodeMenuIntent.ChangeFontSize -> {
                state.update {
                    it.copy(fontSize = intent.fontSize /*when(intent.fontSize){
                        1F ->  3.2F
                        2F ->  3.6F
                        3F ->  4.3F
                        4F ->  5.1F
                        5F ->  5.8F
                        6F ->  7.1F
                        7F ->  8.5F
                        else -> 5.1F
                    }*/,
                    titleProductQRcodeBiteMap = getTitleProductUseCase.execute(
                        intent.title,
                        intent.fontSize
                    )
                    )
                }
            }

            is QRcodeMenuIntent.ChangeHeightQrCode -> {
                state.update {
                    it.copy(heightQRcode = intent.heightQRcode,
                    imgBitmap = getQRcodeBitmapUseCase.execute(
                        intent.dataQRcode ,
                        intent.heightQRcode
                    ))
                }
                }
            is QRcodeMenuIntent.SavedSettings -> {
                state.update {
                    it.copy(isOpenedSettings = false)
                }
            }
            is QRcodeMenuIntent.CloseSettings ->{
                state.update {
                    it.copy(isOpenedSettings = false)
                }
            }
            }
        }
    }
