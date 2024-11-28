package com.profile.profile.screens.ip_camera.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.ip_camera.domain.StreamViewConentUseCase
import com.profile.profile.screens.ip_camera.domain.StartRecordStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StartStreamtUseCase
import com.profile.profile.screens.ip_camera.domain.StopRecordStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StopStreamUseCase
import com.profile.profile.screens.settings.SettingsConnectIpCameraScreen
import com.project.network.Navigation
import com.project.`printer-api`.PrinterScreensApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class IpCameraViewModel(
    private val playStream:StreamViewConentUseCase,
    private val startStreamUseCase: StartStreamtUseCase,
    private val stopStream : StopStreamUseCase,
    private val startRecordStream : StartRecordStreamUseCase,
    private val stopRecordStream : StopRecordStreamUseCase
) : ViewModel() {
    private var isSetedScreen = false
    var isRecorded:MutableState<Boolean> = mutableStateOf(false)

    @Composable
    fun IpCameraView() {

        if (!isSetedScreen) {
            isSetedScreen = true

            playStream.execute("rtsp://192.168.1.150:2000/unicast",)
        }
    }

        fun back(coroutineScope: CoroutineScope) {
            coroutineScope.launch(Dispatchers.IO) {
                val printerScreen: PrinterScreensApi = getKoin().get()
                Navigation.navigator.push(
                    printerScreen.printer()
                )
            }

        }

        fun goToSetting() {
            Navigation.navigator.replaceAll(
                SettingsConnectIpCameraScreen()
            )
        }

    fun clickRecord(scope: CoroutineScope){

        scope.launch {
       if(isRecorded.value){
       //stopStream.execute()
       //stopRecordStream.execute()
           stopRecordStream.execute()
       }else{
           startRecordStream.execute("rtsp://192.168.1.150:2000/unicast")
      // stopRecordStream.execute()
       //startStreamUseCase.execute("rtsp://192.168.1.150:2000/unicast")
       }
            isRecorded.value = !isRecorded.value
    }
}
    }
