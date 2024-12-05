import android.content.Context
import android.os.Handler
import android.os.Message
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.zebra.barcode.sdk.sms.ConfigurationUpdateEvent
import com.zebra.scannercontrol.DCSSDKDefs
import com.zebra.scannercontrol.DCSScannerInfo
import com.zebra.scannercontrol.FirmwareUpdateEvent
import com.zebra.scannercontrol.IDcsSdkApiDelegate
import com.zebra.scannercontrol.SDKHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


    class UsbScannerAndroid(context: Context) : IDcsSdkApiDelegate, UsbScannerApi {

        var sdkHandler: SDKHandler? = null

        var mScannerInfoList: ArrayList<DCSScannerInfo> = ArrayList()

        var connectedScannerId: Int? = null

        var mSNAPIList: ArrayList<DCSScannerInfo> = ArrayList<DCSScannerInfo>()

        var scanData = ""

        val context = context

        override fun customization() {

            sdkHandler = SDKHandler(context)

            sdkHandler?.dcssdkSetDelegate(this)

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

            if (sdkHandler != null) {

                mScannerInfoList.clear();

                sdkHandler?.dcssdkGetAvailableScannersList(mScannerInfoList);

                sdkHandler?.dcssdkGetActiveScannersList(mScannerInfoList);

            }

            if (sdkHandler != null) {

                scannersListHasBeenUpdated()

            } else {

            }

        }

        @Composable
        override fun BarcodeData() {

            Text(scanData, fontSize = 16.sp)

        }

        fun scannersListHasBeenUpdated(): Boolean {

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
                    CoroutineScope(Dispatchers.IO).launch {

                        connectScanner(mSNAPIList[0])

                    }
                }
            }
            return true
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

        fun connectScanner(scanner: DCSScannerInfo): Boolean {

            var result = DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_FAILURE

            if (sdkHandler != null) {
                result =
                    sdkHandler!!.dcssdkEstablishCommunicationSession(scanner.getScannerID())
            }

            if (result == DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_SUCCESS) {

                return true

            } else if (result == DCSSDKDefs.DCSSDK_RESULT.DCSSDK_RESULT_FAILURE) {

                return false
            } else {

                return false

            }


        }


        override fun dcssdkEventScannerAppeared(p0: DCSScannerInfo?) {

        }

        override fun dcssdkEventScannerDisappeared(p0: Int) {

        }


        override fun dcssdkEventCommunicationSessionEstablished(p0: DCSScannerInfo?) {

            connectedScannerId = p0?.getScannerID();

            sdkHandler?.dcssdkEstablishCommunicationSession(connectedScannerId ?: 0);

            if (sdkHandler != null) {

                sdkHandler?.dcssdkEstablishCommunicationSession(connectedScannerId ?: 0);


                Toast.makeText(context, "Подключение к сканеру установлено!", Toast.LENGTH_SHORT)
                    .show();


            } else {

            }

            System.out.println(connectedScannerId);
        }

        override fun dcssdkEventCommunicationSessionTerminated(p0: Int) {
            TODO("Not yet implemented")
        }

        override fun dcssdkEventBarcode(p0: ByteArray?, p1: Int, p2: Int) {

            var barcode = Barcodes(p0, p1, p2);

            dataHandler.obtainMessage(Constants.BARCODE_RECEIVED, barcode).sendToTarget();

        }

        private val dataHandler: Handler = object : Handler() {

            override fun handleMessage(msg: Message) {

                when (msg.what) {

                    Constants.BARCODE_RECEIVED -> {

                        val barcode = msg.obj as Barcodes

                        scanData = "${scanData}\n${String(barcode.getBarcodeData())}"

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
