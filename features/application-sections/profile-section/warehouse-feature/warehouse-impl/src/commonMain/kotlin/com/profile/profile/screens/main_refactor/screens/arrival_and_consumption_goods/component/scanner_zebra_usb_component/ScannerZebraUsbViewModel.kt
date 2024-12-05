package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.scanner_zebra_usb_component

import UsbScannerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.`local-storage`.`profile-storage`.SharedPrefsApi
import org.koin.mp.KoinPlatform

class ScannerZebraUsbViewModel: ViewModel() {

    var state by mutableStateOf(ScannerZebraUsbState())

    fun processIntents( intent: ScannerZebraUsbIntents ) {

        when( intent ){

            is ScannerZebraUsbIntents.SetScreen -> setScreen()

        }

    }

    fun setScreen(){

        //if ( state.isSet ) {

            state = state.copy(

                scannerZebraUsb = KoinPlatform.getKoin().get()

            )

            state.scannerZebraUsb!!.customization()
        //}

    }

}