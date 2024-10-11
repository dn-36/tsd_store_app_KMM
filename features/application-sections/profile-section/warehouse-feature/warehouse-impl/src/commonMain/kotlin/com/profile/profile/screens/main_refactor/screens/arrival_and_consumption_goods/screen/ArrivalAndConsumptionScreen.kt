package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse


class ArrivalAndConsumptionScreen: Screen {
    val vm = ArrivalAndConsumptionViewModel()
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Приход Расход", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                Column {
                    Row(modifier = Modifier.padding(10.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(
                            onClick = { vm.processIntent(ArrivalAndConsumptionIntents.Arrival(vm)) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.45f)
                        ) {
                            Text(text = "Приход")
                        }
                        Button(
                            onClick = {},
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.9f)
                        ) {
                            Text(text = "Расход")
                        }
                    }
                    MenuBottomBarWarehouse().Content()
                }
            }
        }
    }
}