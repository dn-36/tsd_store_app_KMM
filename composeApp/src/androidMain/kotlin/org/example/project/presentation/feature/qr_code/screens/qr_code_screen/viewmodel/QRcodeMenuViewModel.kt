package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.project.phone.VKPUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.feature.qr_code.screens.product_search.ui.ProductSearchScreen
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.ConectUSBUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.ConnectToBleutoothDeviceUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.GetListBluetoothDeviceUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.GetQRcodeBitmapUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.GetTitleProductBiteMapUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.PrintOnTscUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.PrintOnVkpUseCase
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.domain.usecases.StopBluetoothDiscovery
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.viewmodel.model.CategoryPrinter
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.viewmodel.model.ConnectionDeviseStatus
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.viewmodel.model.StatusBluetoothLoading

class QRcodeMenuViewModel(
    private val conectUSBUseCase: ConectUSBUseCase,
    private val printerVkpUseCase: PrintOnVkpUseCase,
    private val getQRcodeBitmapUseCase: GetQRcodeBitmapUseCase,
    private val getTitleProductUseCase: GetTitleProductBiteMapUseCase,
    private val getListDeviceBluetooth: GetListBluetoothDeviceUseCase,
    private val connectToBluetoothDeviceUseCase: ConnectToBleutoothDeviceUseCase,
    private val printOnTscUseCase:PrintOnTscUseCase<Bitmap>,
    private val stopBluetoothDiscovery: StopBluetoothDiscovery
) : ViewModel() {

    val state = MutableStateFlow(QRCodeMenuState())
    private var isSetedScreen = false

    private var x: Int = 0
    private var y: Int = 0
    private var height: Int = 40
    private var weight: Int = 40

    fun processIntent(intent: QRcodeMenuIntent) {

        when (intent) {
            is QRcodeMenuIntent.SetScreen -> {
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
                state.update { state.value.copy(isLoadingDataOnScreen = false) }
                NavigatorComponent.navigator = intent.navigator
                conectUSBUseCase.execute()

            }

            is QRcodeMenuIntent.OpenProductSearch -> {
                NavigatorComponent.navigator!!.push(ProductSearchScreen)
            }

            is QRcodeMenuIntent.PrintQRcode -> {
                //Log.d("testssss",state.value.titleProductQRcodeBiteMap!!.width.toString()/50)
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

                      val  title = getTitleProductUseCase
                            .execute(
                                intent.product.title,
                                intent.product.fontSize
                            )

                        printOnTscUseCase.execute(
                          state.value.imgBitmap!!,
                            VKPUtils.setSizeBitMap(
                             title,
                              title.width/13,
                               title.height/13,
                                intent.context,
                            )
                            ,
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
  /*when(state.value.categoryPrinter){
     CategoryPrinr.VKP ->{*/state.update { it.copy(isOpenedSettingsVKP = true) }/*}
    CategoryPrinter.TSC ->{
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
               Log.d("tests_qqq","result")
           })
       }

    }
    CategoryPrinter.ZEBRA ->{}
 }*/

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
 state.update {
    it.copy(isOpenedSettingsVKP = false)
}
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

is QRcodeMenuIntent.SelectBluetoothDevice -> {

 intent.scope.launch(Dispatchers.IO) {
     connectToBluetoothDeviceUseCase.execute(intent.device,{
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
                if(intent.x<0) x = 0 else x = (intent.x*1.25).toInt()
                if(intent.y<0) y = 0 else y = (intent.y*1.25).toInt()

                height = intent.height
                weight = intent.weight
            }
        }
}
}


