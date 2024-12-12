package com.arrival_and_consumption_goods.viewmodel

import android.content.Context
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arrival_and_consumption_goods.helpers.Barcode
import com.arrival_and_consumption_goods.helpers.Constants
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.zebra.barcode.sdk.sms.ConfigurationUpdateEvent
import com.zebra.scannercontrol.DCSSDKDefs
import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.FirmwareUpdateEvent
import com.zebra.scannercontrol.IDcsSdkApiDelegate
import com.zebra.scannercontrol.SDKHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScannerZebraUsbViewModel( val context: Context ): ViewModel(), IDcsSdkApiDelegate {

    var state by mutableStateOf(ScannerZebraUsbState())

    fun processIntents( intent: ScannerZebraUsbIntents ) {

        when( intent ) {

            is ScannerZebraUsbIntents.UpdateScanData -> updateScanData(intent.text)

            is ScannerZebraUsbIntents.CheckSku -> checkSku( intent.sku, intent.listProducts )

        }

    }

    init {
        // Запускаем корутину в фоновом потоке
        viewModelScope.launch(Dispatchers.IO) {
            // Этот код будет выполняться пока ViewModel существует
            while (isActive) {
                // Вызов вашей функции или операции, которая должна выполняться непрерывно
                if ( state.sdkHandler != null ) {

                scannersListHasBeenUpdated()

                }

                // При необходимости задержка перед повторным вызовом
                delay(1000L)
            }
        }
    }

    fun checkSku( sku: String, listProducts: List<AllProductArrivalAndConsumption> ) {

        val selectedProduct = listProducts.find { it.sku == sku }

        if (selectedProduct != null) {

            state = state.copy(

                checkSku = true

            )

        } else {

            state = state.copy(

                checkSku = false,

                textNewProduct = "Продукт в списке не обнаржуен \nВы хотите его добавить?"

            )

        }

    }

    fun updateScanData(text: String) {

        state = state.copy(

            scanData = text

        )

    }

    fun customization() {

            state.sdkHandler = SDKHandler(context)

            state.sdkHandler?.dcssdkSetDelegate(this)

            state.sdkHandler!!.dcssdkSetOperationalMode(DCSSDKDefs.DCSSDK_MODE.DCSSDK_OPMODE_SNAPI)

            var notifications_mask = 0

            notifications_mask =

                notifications_mask or (DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SCANNER_APPEARANCE.value or

                        DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SCANNER_DISAPPEARANCE.value)



            notifications_mask =

                notifications_mask or (DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SESSION_ESTABLISHMENT.value or

                        DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SESSION_TERMINATION.value)


            notifications_mask =

                notifications_mask or DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_BARCODE.value


            state.sdkHandler!!.dcssdkSubsribeForEvents(notifications_mask)


            state.sdkHandler!!.dcssdkEnableAvailableScannersDetection(true)

            if ( state.sdkHandler != null) {

                state.mScannerInfoList.clear();

                state.sdkHandler?.dcssdkGetAvailableScannersList(state.mScannerInfoList);

                state.sdkHandler?.dcssdkGetActiveScannersList(state.mScannerInfoList);
            }

    }

    suspend fun scannersListHasBeenUpdated() {

        state.mSNAPIList.clear()

        updateScannersList()

        for ( device in state.mScannerInfoList ) {

            if ( device.connectionType == DCSSDKDefs.DCSSDK_CONN_TYPES.DCSSDK_CONNTYPE_USB_SNAPI ) {

                state.mSNAPIList.add(device)
            }
        }

        if ( state.mSNAPIList.size == 1 ) {

            // Only one SNAPI scanner available
            if (state.mSNAPIList[0].isActive) {

                // Available scanner is active. Navigate to active scanner
            } else {

             connectScanner( state.mSNAPIList[0])

            }

        }

    }

    fun updateScannersList() {

        if ( state.sdkHandler != null) {

             state.mScannerInfoList.clear()

            val scannerTreeList = java.util.ArrayList<DCSScannerInfo>()

            state.sdkHandler!!.dcssdkGetAvailableScannersList(scannerTreeList)

            state.sdkHandler!!.dcssdkGetActiveScannersList(scannerTreeList)

            createFlatScannerList(scannerTreeList)
        }
    }

    private fun createFlatScannerList(scannerTreeList: java.util.ArrayList<DCSScannerInfo>) {

        for (s in scannerTreeList) {

            addToScannerList(s)
        }
    }

    private fun addToScannerList(s: DCSScannerInfo) {

        state.mScannerInfoList.add(s)

        if (s.auxiliaryScanners != null) {

            for (aux in s.auxiliaryScanners.values) {

                addToScannerList(aux)

            }
        }
    }

    suspend fun connectScanner( scanner: DCSScannerInfo ): Boolean {

        var result = DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_FAILURE

            result = state.sdkHandler!!.dcssdkEstablishCommunicationSession(scanner.getScannerID())

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Подключение к сканеру установлено!", Toast.LENGTH_SHORT)
                    .show();

                state = state.copy(

                    connectedScannerId = scanner.getScannerID()

                )

            }


        if (result == DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_SUCCESS) {

            return true

        } else if (result == DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_FAILURE) {

            return false
        }
        else {

            return false

        }


    }


    override fun dcssdkEventScannerAppeared(p0: DCSScannerInfo?) {

    }

    override fun dcssdkEventScannerDisappeared(p0: Int) {

        dataHandler.obtainMessage(Constants.SCANNER_DISAPPEARED, state.connectedScannerId).sendToTarget()

    }

    override fun dcssdkEventCommunicationSessionEstablished(p0: DCSScannerInfo?) {

        /*  connectedScannerId = p0?.getScannerID();

          sdkHandler?.dcssdkEstablishCommunicationSession(connectedScannerId?:0);

          if (sdkHandler != null) {

              sdkHandler?.dcssdkEstablishCommunicationSession(connectedScannerId?:0);

              //scanData = "Подключение к сканеру установлено!"



          } else {

          }

          System.out.println(connectedScannerId);*/
    }

    override fun dcssdkEventCommunicationSessionTerminated(p0: Int) {

        dataHandler.obtainMessage(
            Constants.SESSION_TERMINATED,
            state.connectedScannerId
        ).sendToTarget()

    }



    override fun dcssdkEventBarcode(p0: ByteArray?, p1: Int, p2: Int) {

        var barcode = Barcode(
            p0 ?: ByteArray(0), // Защита от null: если p0 == null, создаем пустой массив
            p1,
            p2
        )

        state = state.copy(

            scanData = ""

        )

        dataHandler.obtainMessage(Constants.BARCODE_RECEIVED, barcode). sendToTarget();



    }

    private val dataHandler: Handler = object : Handler() {

        override fun handleMessage(msg: Message) {

            when (msg.what) {

                Constants.BARCODE_RECEIVED -> {

                    val barcode = msg.obj as Barcode

                    // viewModel.processIntents(

                    //  ScannerZebraUsbIntents.UpdateScanData("${String(barcode.barcodeData)}"))

                    state = state.copy (

                        scanData = String(barcode.barcodeData)

                    )

                    //scannersListHasBeenUpdated()

                    //println("///// handleMessage ${state.scanData}//////")


                    //Toast.makeText(context, "Данные ${String(barcode.barcodeData)}", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    override fun dcssdkEventImage(p0: ByteArray?, p1: Int) {

    }

    override fun dcssdkEventVideo(p0: ByteArray?, p1: Int) {

    }

    override fun dcssdkEventBinaryData(p0: ByteArray?, p1: Int) {

    }

    override fun dcssdkEventFirmwareUpdate(p0: FirmwareUpdateEvent?) {

    }

    override fun dcssdkEventAuxScannerAppeared(p0: DCSScannerInfo?, p1: DCSScannerInfo?) {

    }

    override fun dcssdkEventConfigurationUpdate(p0: ConfigurationUpdateEvent?) {

    }


}
