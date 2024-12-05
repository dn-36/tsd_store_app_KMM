package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods;

import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profile.Barcode
import com.zebra.barcode.sdk.sms.ConfigurationUpdateEvent
import com.zebra.scannercontrol.DCSSDKDefs
import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.FirmwareUpdateEvent
import com.zebra.scannercontrol.IDcsSdkApiDelegate
import com.zebra.scannercontrol.SDKHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.mp.KoinPlatform.getKoin


actual class ScannerZebraUsbScreen actual constructor( ): IDcsSdkApiDelegate {

    val viewModel = ScannerZebraUsbViewModel(getKoin().get())

    val context = viewModel.context

    var sdkHandler:SDKHandler? = null

    var mScannerInfoList: ArrayList<DCSScannerInfo>  = ArrayList()

    var  mSNAPIList: ArrayList<DCSScannerInfo> = ArrayList<DCSScannerInfo>()

    var scanData = mutableStateOf("")



    @Composable

     actual fun Content() {

        val scope = rememberCoroutineScope()

        customization()

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                Text(

                    text = "Подсоедините кабель сканера к телефону и разрешите подключиться к нему нажав затем кнопку да",

                    modifier = Modifier.fillMaxWidth(0.9f), fontSize = 16.sp,

                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = scanData.value)

                Text("подключился", modifier = Modifier.clickable {

                    scannersListHasBeenUpdated()


                })

            }


        }
    }

    fun customization() {

            sdkHandler = SDKHandler(context)

            sdkHandler?.dcssdkSetDelegate(ScannerZebraUsbScreen())

            sdkHandler!!.dcssdkSetOperationalMode(DCSSDKDefs.DCSSDK_MODE.DCSSDK_OPMODE_SNAPI)

            var notifications_mask = 0

            notifications_mask =

                notifications_mask or (DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SCANNER_APPEARANCE.value or

                        DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SCANNER_DISAPPEARANCE.value)



            notifications_mask =
                notifications_mask or (DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SESSION_ESTABLISHMENT.value or

                        DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_SESSION_TERMINATION.value)


            notifications_mask =

                notifications_mask or DCSSDKDefs.DCSSDK_EVENT.DCSSDK_EVENT_BARCODE.value


            sdkHandler!!.dcssdkSubsribeForEvents(notifications_mask)


            sdkHandler!!.dcssdkEnableAvailableScannersDetection(true)

        if ( sdkHandler != null ) {

            mScannerInfoList.clear();

            sdkHandler?.dcssdkGetAvailableScannersList(mScannerInfoList);

            sdkHandler?.dcssdkGetActiveScannersList(mScannerInfoList);
        }

    }

    fun scannersListHasBeenUpdated() {

        mSNAPIList.clear()

        updateScannersList()

        for (device in mScannerInfoList) {
            if (device.connectionType == DCSSDKDefs.DCSSDK_CONN_TYPES.DCSSDK_CONNTYPE_USB_SNAPI) {
                mSNAPIList.add(device)
            }
        }
        if (mSNAPIList.size == 1) {
            // Only one SNAPI scanner available
            if (mSNAPIList[0].isActive) {
                // Available scanner is active. Navigate to active scanner
            } else {

                CoroutineScope(Dispatchers.IO).launch{

                    connectScanner(mSNAPIList[0])


                }

            }

        }

    }

    fun updateScannersList() {

        if (sdkHandler != null) {

            mScannerInfoList.clear()

            val scannerTreeList = java.util.ArrayList<DCSScannerInfo>()

            sdkHandler!!.dcssdkGetAvailableScannersList(scannerTreeList)

            sdkHandler!!.dcssdkGetActiveScannersList(scannerTreeList)

            createFlatScannerList(scannerTreeList)
        }
    }

    private fun createFlatScannerList(scannerTreeList: java.util.ArrayList<DCSScannerInfo>) {

        for (s in scannerTreeList) {

            addToScannerList(s)
        }
    }

    private fun addToScannerList(s: DCSScannerInfo) {

        mScannerInfoList.add(s)

        if (s.auxiliaryScanners != null) {

            for (aux in s.auxiliaryScanners.values) {

                addToScannerList(aux)

            }
        }
    }

    suspend fun connectScanner( scanner: DCSScannerInfo ): Boolean {

        var result = DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_FAILURE

        if (sdkHandler != null) { result = sdkHandler!!.dcssdkEstablishCommunicationSession(scanner.getScannerID())

            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Подключение к сканеру установлено!", Toast.LENGTH_SHORT)
                    .show();

            }
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
        TODO("Not yet implemented")
    }



    override fun dcssdkEventBarcode(p0: ByteArray?, p1: Int, p2: Int) {

        val barcode = Barcode(
            p0 ?: ByteArray(0), // Защита от null: если p0 == null, создаем пустой массив
            p1,
            p2
        )

        dataHandler.obtainMessage(Constants.BARCODE_RECEIVED, barcode). sendToTarget();

    }

    private val dataHandler: Handler = object : Handler() {

        override fun handleMessage(msg: Message) {

            when (msg.what) {

                Constants.BARCODE_RECEIVED -> {

                    val barcode = msg.obj as Barcode

                    scanData.value = "${scanData.value}\n${String(barcode.barcodeData)}"

                    Log.d("data", String(barcode.barcodeData))

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