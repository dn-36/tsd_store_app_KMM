package com.profile.profile.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.arrival_and_consumption_goods.component.scanner_camera_component.ui.ScannerCameraComponent
import com.profile.profile.screens.ip_camera.IpCameraScreen
//import com.project.core_app.components.ScannerComponent
import com.project.network.Navigation
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.copy
import kotlin.jvm.Transient

class SettingsConnectIpCameraScreen():Screen {

    @Transient val dataWofi = mutableStateOf<DataWifi?>(null)

    @Composable
    override fun Content() {



        Box {
            //ScannerCaComponent().Show(
               ScannerCameraComponent({},{},{}, listOf())
               /* "Сканер",
                "Подключиться к сети",
                isShowInfo = false,
                {

                    openWifiSettings()
                },
                { Navigation.navigator.push(IpCameraScreen()) },
                { result ->
                    dataWofi.value =  parseWifiData(result)
                }
            )
                */
            if((dataWofi.value?.name?:"").isNotBlank()) {
            Column(Modifier.padding(start = 10.dp, bottom = 90.dp).align(Alignment.BottomStart)) {
                Row(modifier = Modifier.height(40.dp)) {
                    Text("Названия сети: ", fontSize = 15.sp,  modifier =  Modifier.align(Alignment.CenterVertically))
                    Text(" "+dataWofi.value?.name, fontSize = 15.sp, fontWeight = FontWeight.Bold,  modifier =  Modifier.align(Alignment.CenterVertically))
                }
                Spacer(Modifier.size(height = 20.dp, width = 0.dp))
                Row(modifier = Modifier.height(40.dp)){
                    Text("Пороль: ", fontSize = 15.sp,modifier =  Modifier.align(Alignment.CenterVertically))
                    Text(" "+dataWofi.value?.password, fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier =  Modifier.align(Alignment.CenterVertically))
                    Spacer(Modifier.width(15.dp))

                        Image(
                            painter = painterResource(resource = Res.drawable.copy),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.CenterVertically)
                                .clickable {
                                    saveToClipboard("password",dataWofi.value?.password?:"")
                                }
                        )
                    }
                }


            }

        }
    }
   data class DataWifi(val name: String, val password: String)

   private fun parseWifiData(input: String): DataWifi {
        val parts = input.split("/")
        val name = parts.getOrNull(0)?.trim() ?: ""
        val password = parts.getOrNull(1)?.trim() ?: ""
        return DataWifi(name, password)
    }
}

expect fun openWifiSettings()

expect fun saveToClipboard(label: String, text: String)