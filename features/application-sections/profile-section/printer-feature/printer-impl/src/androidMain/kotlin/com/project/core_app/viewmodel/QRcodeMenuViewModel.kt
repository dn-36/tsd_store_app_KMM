package com.project.core_app.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.profile.profile.udpPlayer.screens.product_search.ui.ProductSearchScreen
import com.project.core_app.usecases.ConectUSBUseCase
import com.project.core_app.usecases.ConnectToBleutoothDeviceUseCase
import com.project.core_app.usecases.GetListBluetoothDeviceUseCase
import com.project.core_app.usecases.GetQRcodeBitmapUseCase
import com.project.core_app.usecases.GetTitleProductBiteMapUseCase
import com.project.core_app.usecases.PrintOnTscUseCase
import com.project.core_app.usecases.PrintOnVkpUseCase
import com.project.core_app.usecases.StopBluetoothDiscovery
import com.project.core_app.viewmodel.model.CategoryPrinter
import com.project.core_app.viewmodel.model.ConnectionDeviseStatus
import com.project.core_app.viewmodel.model.StatusBluetoothLoading
import com.project.network.Navigation
import com.project.phone.VKPUtils

class QRcodeMenuViewModel(
    private val conectUSBUseCase: ConectUSBUseCase,
    private val printerVkpUseCase: PrintOnVkpUseCase,
    private val getQRcodeBitmapUseCase: GetQRcodeBitmapUseCase,
    private val getTitleProductUseCase: GetTitleProductBiteMapUseCase,
    private val getListDeviceBluetooth: GetListBluetoothDeviceUseCase,
    private val connectToBluetoothDeviceUseCase: ConnectToBleutoothDeviceUseCase,
    private val printOnTscUseCase: PrintOnTscUseCase<Bitmap>,
    private val stopBluetoothDiscovery: StopBluetoothDiscovery
) : ViewModel() {

    val state = MutableStateFlow(QRCodeMenuState())
    private var isSetedScreen = false
    private var x: Int = 0
    private var y: Int = 0
    private var height: Int = 40
    private var weight: Int = 40
    private val CEF_FONT_SIZE: Int = 10


    fun processIntent(intent: QRcodeMenuIntent) {

        when (intent) {
            is QRcodeMenuIntent.SetScreen -> {
                if (isSetedScreen) return
                isSetedScreen = true
               state.update {
                   it.copy(
                       qrCodeDataText = if(!intent.product.title.isNullOrBlank())
                           intent.product.title
                       else
                           state.value.qrCodeDataText
                   )
               }
                val qrCodeDataText = if(!intent.product.qrCodeData.isNullOrBlank())
                    intent.product.qrCodeData
                else state.value.qrCodeDataText

                val titleProduct = if(!intent.product.title.isNullOrBlank())
                    intent.product.title
                else
                    state.value.qrCodeDataText

                val qrCodeBiteMap = getQRcodeBitmapUseCase
                    .execute(
                        qrCodeDataText,
                        state.value.heightQRcode,
                        state.value.barCodeWidth
                    )
               // qrCodeBiteMap.width = qrCodeBiteMap.width * 1.3F
                state.value = state.value.copy(imgBitmap = qrCodeBiteMap)

                state.value = state.value.copy(
                    titleProductQRcodeBiteMap = getTitleProductUseCase.execute(
                        titleProduct,
                        intent.product.fontSize+CEF_FONT_SIZE
                    )
                )
                state.update { state.value.copy(isLoadingDataOnScreen = false) }
                Navigation.navigator = intent.navigator
                conectUSBUseCase.execute()

            }

            is QRcodeMenuIntent.OpenProductSearch -> {
                Navigation.navigator!!.push(ProductSearchScreen)
            }

            is QRcodeMenuIntent.PrintQRcode -> {

                when(state.value.categoryPrinter){
                    CategoryPrinter.VKP -> {
                        printerVkpUseCase.execute(
                            state.value.qrCodeDataText,
                            state.value.qrCodeDataText,
                            heightQRCodeMM = intent.product.heightQRcode,
                            fontSize = intent.product.fontSize+CEF_FONT_SIZE
                        )
                    }
                    CategoryPrinter.TSC -> {

                      val  title = getTitleProductUseCase
                            .execute(
                                intent.product.title,
                                intent.product.fontSize*2
                            )

                        printOnTscUseCase.execute(
                          state.value.imgBitmap!!,
                            VKPUtils.setSizeBitMap(
                             title,
                              title.width/13,
                               title.height/13,
                                intent.context,
                            ),
                            height,weight,
                            x,y
                          )



                    }
                    CategoryPrinter.ZEBRA ->{

                    }
                }

            }
            is QRcodeMenuIntent.OpenSettingsBluetooth ->{
                state.update {
                    state.value.copy(
                        bluetoothDeviceList = listOf(),
                        isOpenedBluetoothSettingsTSC = true,
                        statusSearchBluetoothDevice = StatusBluetoothLoading.LOADING
                    )
                }
                intent.scope.launch {
                    getListDeviceBluetooth.execute({
                        val list = state.value.bluetoothDeviceList.toMutableList()
                        list.add(it)
                        state.update { state.value.copy(
                            bluetoothDeviceList = list
                        ) }
                    },
                        {
                            state.update { state.value.copy(
                                statusSearchBluetoothDevice = StatusBluetoothLoading.SUCCSSFULL
                            ) }
                        })
                }

            }

             is QRcodeMenuIntent.OpenSettingsPrinter -> {
                 state.update { it.copy(isOpenedSettingsVKP = true)
                 }
}

is QRcodeMenuIntent.ChangeFontSize -> {
 state.update {
     it.copy(
     fontSize = intent.fontSize ,
     titleProductQRcodeBiteMap = getTitleProductUseCase.execute(
         intent.title,
         intent.fontSize+CEF_FONT_SIZE
     )
     )
 }
}

is QRcodeMenuIntent.ChangeHightQrCode -> {
 state.update {
     it.copy(
     heightQRcode = intent.heightQRcode,
     imgBitmap = getQRcodeBitmapUseCase.execute(
         intent.dataQRcode ,
         intent.heightQRcode,
         state.value.barCodeWidth
     )
     )
 }
 }
is QRcodeMenuIntent.SavedSettings -> {
    state.value.titleProductQRcodeBiteMap?.width
    var _x = (state.value.weightTicketTsc ) - ((state.value.imgBitmap?.width?:0 / 4))
    var _y = ((state.value.heightTicketTsc) -
            (state.value.imgBitmap?.height?:0 / 4 +
            (state.value.titleProductQRcodeBiteMap?.width?:0 / 4))) + 30
    _x = (_x*1.5).toInt()
    _y = (_y*1.5).toInt()
    x = _x
    y = _y
 state.update {
    it.copy(
        isOpenedSettingsVKP = false,
        x = _x.toFloat(),
        y = _y.toFloat()
        )
}
}
is QRcodeMenuIntent.CloseSettingsVKP -> {

    state.value.titleProductQRcodeBiteMap?.width
        var x = (state.value.weightTicketTsc * 2.25F) - ((state.value.imgBitmap?.width?:0 / 4))
        var y = ((state.value.heightTicketTsc * 2.25F) -
                (state.value.imgBitmap?.height?:0 / 4 +
                (state.value.titleProductQRcodeBiteMap?.width?:0 / 4))) + 30
    x = (x*1.5).toInt().toFloat()
    y = (y*1.5).toInt().toFloat()


 state.update {
     it.copy(
         isOpenedSettingsVKP = false,
         x = x,
         y = y
     )
 }
}
is QRcodeMenuIntent.СhoicePrinter -> {
state.update {
  it.copy(
      categoryPrinter = intent.category
  )
}
 }

is QRcodeMenuIntent.SelectBluetoothDevice -> {

 intent.scope.launch(Dispatchers.IO) {
     connectToBluetoothDeviceUseCase.execute(
         intent.device,{
         state.update {
             it.copy(
             isLoadingConnectionDevice = false,
             statusConnected = ConnectionDeviseStatus.IsConnected,
             )
         }
     },{
         state.update {
             it.copy(
                 isLoadingConnectionDevice = false,
                 statusConnected = ConnectionDeviseStatus.IsNotConnected
             )
         }
     })
     stopBluetoothDiscovery.execute()
 }
 state.update {
     it.copy(
         deviceTitle = intent.device,
         isLoadingConnectionDevice = true,
         statusConnected = ConnectionDeviseStatus.NoShowStatus,
         isOpenedBluetoothSettingsTSC = false)
 }
}
is QRcodeMenuIntent.SearchBluetoothDevice -> {
 state.update {
     state.value.copy(
         bluetoothDeviceList = listOf(),
         isOpenedBluetoothSettingsTSC = true,
         statusSearchBluetoothDevice = StatusBluetoothLoading.LOADING
     )
 }

 intent.scope.launch {
     getListDeviceBluetooth.execute({
         val list = state.value.bluetoothDeviceList.toMutableList()
         list.add(it)
         state.update { state.value.copy(
             isOpenedBluetoothSettingsTSC = true,
             bluetoothDeviceList = list
         ) }
     },
         {
             state.update {
                 state.value.copy(
                 statusSearchBluetoothDevice = StatusBluetoothLoading.SUCCSSFULL
             )
             }
         })
 }
}

is QRcodeMenuIntent.CloseSettingsBluetooth -> {
 state.update {
     state.value.copy(
         isOpenedBluetoothSettingsTSC = false,
         isLoadingConnectionDevice = false,
         statusConnected = ConnectionDeviseStatus.NoShowStatus
     )
 }
     intent.scope.launch {
         stopBluetoothDiscovery.execute()
     }

 }

            is QRcodeMenuIntent.ChangeSettingsTsc -> {

               if(intent.x<0) x = 0 else x = (intent.x*1.1).toInt()
               if(intent.y<0) y = 0 else y = (intent.y*1.1).toInt()

                height = intent.height
                weight = intent.weight
            }

            is QRcodeMenuIntent.InputTextProduct -> state.update {
                it.copy(
                    qrCodeDataText = intent.text
                )
            }

            is QRcodeMenuIntent.ChangeWidthQrCode -> {
                when(intent.widthQRcode.toInt()) {

                    1  -> {
                        state.value = state.value.copy(barCodeWidth = 1F)
                    }
                    2  -> {

                        state.value = state.value.copy(barCodeWidth = 1.25F)

                    }
                    3 -> {

                        state.value = state.value.copy(barCodeWidth = 1.94F)

                    }

                    else -> {

                    }

                }

                val barCode = getQRcodeBitmapUseCase.execute(
                    state.value.qrCodeDataText,
                    state.value.heightQRcode,
                    state.value.barCodeWidth
                    //intent.widthQRcode
                )
                if(intent.widthQRcode.toInt() != 4){
                state.update {
                    it.copy(
                        imgBitmap = barCode,
                        barCodeWidth =
                        intent.widthQRcode.toInt().toFloat()
                    )
                }
                }

            }
        }
}
}


