package com.profile.profile.screens.ip_camera.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import com.profile.profile.screens.ip_camera.domain.InfrastructureIpCameraApi
import com.profile.profile.screens.ip_camera.domain.StreamViewConentUseCase
import com.profile.profile.screens.ip_camera.domain.StartRecordStreamUseCase
import com.profile.profile.screens.ip_camera.domain.StopRecordStreamUseCase
import com.profile.profile.screens.settings.SettingsConnectIpCameraScreen
import com.project.core_app.utils.Stopwatch
import com.project.network.Navigation
import com.project.`printer-api`.PrinterScreensApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatform.getKoin

class IpCameraViewModel() : ViewModel() {
    private val infrastructure: InfrastructureIpCameraApi = getKoin().get()
    private val playStream:StreamViewConentUseCase = StreamViewConentUseCase(infrastructure)
    private val startRecordStream : StartRecordStreamUseCase = StartRecordStreamUseCase(infrastructure)
    private val stopRecordStream : StopRecordStreamUseCase = StopRecordStreamUseCase(infrastructure)
    val state:MutableState<IpCameraState> = mutableStateOf(IpCameraState())
    private val stopwatch: Stopwatch = Stopwatch()
    private var isSetedScreen = false
    private val URL_STREAM = "rtsp://192.168.1.150:2000/unicast"

  /*  fun processIntent( ipCameraIntent:IpCameraIntent){
        when(ipCameraIntent){
           is IpCameraIntent.IpCameraView -> {
                IpCameraView()
            }
        }

    }*/

    @Composable
    fun IpCameraView() {
    val scope = rememberCoroutineScope()
        if (!isSetedScreen) {
            isSetedScreen = true
            scope.launch(Dispatchers.IO) {
                stopwatch.timeFlow.collect{
                    state.value = state.value.copy(it)
                }
            }


            playStream.execute(URL_STREAM)
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
       if(state.value.isRecorded){
           stopwatch.reset()
           stopRecordStream.execute()
       }else{
           stopwatch.start(scope)
           startRecordStream.execute(URL_STREAM)
       }
            state.value = state.value.copy(isRecorded = !state.value.isRecorded)

    }
}
    }
