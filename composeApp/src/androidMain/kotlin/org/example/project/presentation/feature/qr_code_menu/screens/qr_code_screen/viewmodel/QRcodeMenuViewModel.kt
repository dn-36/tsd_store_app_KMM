package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel

import android.bluetooth.BluetoothDevice
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.printer_barcode.TSCprinter
import com.project.printer_barcode.VKPUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.feature.qr_code_menu.screens.product_search.ui.ProductSearchScreen
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.ConnectToBleutoothDeviceUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetListBluetoothDeviceUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetQRcodeBitmapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnTscUseCase
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.domain.usecases.PrintOnVkpUseCase
import org.koin.mp.KoinPlatform.getKoin

class QRcodeMenuViewModel(
    private val conectUSBUseCase: ConectUSBUseCase,
    private val printerVkpUseCase: PrintOnVkpUseCase,
    private val getQRcodeBitmapUseCase: GetQRcodeBitmapUseCase,
    private val getTitleProductUseCase: GetTitleProductBiteMapUseCase,
    private val getListDeviceBluetooth: GetListBluetoothDeviceUseCase,
    private val connectToBleutoothDeviceUseCase: ConnectToBleutoothDeviceUseCase,
    private val printOnTscUseCase:PrintOnTscUseCase<Bitmap>
) : ViewModel() {

    val state = MutableStateFlow(QRCodeMenuState())
    private var isSetedScreen = false


    fun processIntent(intent: QRcodeMenuIntent) {

        when (intent) {
            is QRcodeMenuIntent.SetScreen -> {
                if (isSetedScreen) return
                isSetedScreen = true
               // var device:BluetoothDevice? = null
               // printer.searchForDevices({Log.d("opop",it)},{})

//                TSCprinter.init(device!!)
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
                when(state.value.categoryPrinter){
                    CategoryPrinter.VKP -> {
                        printerVkpUseCase.execute(
                            intent.product.qrCodeData?:"",//"QR code",
                            intent.product.title,//"Описание",
                            heightQRCodeMM = intent.product.heightQRcode,
                            fontSize = intent.product.fontSize
                        )
                    }
                    CategoryPrinter.TSC -> {
                        printOnTscUseCase.execute(
                            VKPUtils.textToBitmap("product tsd store",160,7F,true)!!,
                            VKPUtils.textToBitmap("product tsd store",50,7F,true)!!,
                        )
                    }
                    CategoryPrinter.ZEBRA ->{

                    }
                }

            }

            is QRcodeMenuIntent.OpenSettingsPrinter -> {
                when(state.value.categoryPrinter){
                   CategoryPrinter.VKP ->{state.update { it.copy(isOpenedSettingsVKP = true) }}
                   CategoryPrinter.TSC ->{
                       state.update { state.value.copy(isOpenedSettingsTSC = true) }

                       getListDeviceBluetooth.execute({
                           Log.d("tests_qqq",it)
                           val list = state.value.bluetoothDeviceList.toMutableList()
                           list.add(it)
                           state.update { state.value.copy(
                               isOpenedSettingsTSC = true,
                               bluetoothDeviceList = list
                               ) }
                       },{
                           Log.d("tests_qqq","result")
                       })
                           //isOpenedSettingsTSC = true)

                   }
                   CategoryPrinter.ZEBRA ->{}
                }

            }

            is QRcodeMenuIntent.ChangeFontSize -> {
                state.update {
                    it.copy(fontSize = intent.fontSize ,
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
             //   state.update {
                  //  it.copy(isOpenedSettingsVKP = false)
             //   }
            }
            is QRcodeMenuIntent.CloseSettingsVKP -> {
                state.update {
                    it.copy(isOpenedSettingsVKP = false)
                }
            }
            is QRcodeMenuIntent.СhoicePrinter -> {
             state.update {
                 it.copy(
                     categoryPrinter = intent.category
                 )
             }
                }

            is QRcodeMenuIntent.CloseSettingsTsc ->{
                connectToBleutoothDeviceUseCase.execute(intent.device)
                state.update {
                    it.copy(isOpenedSettingsTSC = false)
                }

            }

        }
            }
        }

