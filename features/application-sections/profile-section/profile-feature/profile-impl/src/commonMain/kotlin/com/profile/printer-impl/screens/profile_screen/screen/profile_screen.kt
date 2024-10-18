package com.profile.profile.screens.profile_screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.core_app.menu_bottom_bar.ui.Section
import org.example.project.presentation.profile_feature.main_feature.viewmodel.ProfileIntents
import org.example.project.presentation.profile_feature.main_feature.viewmodel.ProfileViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.down_arrow
import project.core.resources.photo_profie

object ProfileScreen:Screen{

    val vm = ProfileViewModel()
    @Composable
    override fun Content() {

        val scope = rememberCoroutineScope()

        vm.processIntent(ProfileIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)){
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.2f).background(Color.White))
            Column {
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f)
                        .clip(RoundedCornerShape(15.dp)).background(Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Профиль", color = Color.Black, fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(30.dp))

                        Column(horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()) {
                            Text(vm.profileState.name, fontSize = 20.sp)

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(vm.profileState.numberPhone, fontSize = 15.sp, color = Color.LightGray)

                            Spacer(modifier = Modifier.height(15.dp))

                            Image(
                                painter = painterResource(Res.drawable.photo_profie),
                                contentDescription = null,
                                modifier = Modifier.size(150.dp)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.95f)
                        .clip(RoundedCornerShape(15.dp)).background(Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp).align(Alignment.TopCenter)
                        , horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(modifier = Modifier.fillMaxWidth(0.9f)
                        , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                            Text("Контроль проектов", fontSize = 15.sp)
                            Image(painter = painterResource(Res.drawable.down_arrow),contentDescription = null,
                                modifier = Modifier.size(20.dp).graphicsLayer(rotationZ = 270f))
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(modifier = Modifier.fillMaxWidth(0.9f).clickable (
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { vm.processIntent(ProfileIntents.Warehouse) }
                            , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                            Text("Склад", fontSize = 15.sp)
                            Image(painter = painterResource(Res.drawable.down_arrow),contentDescription = null,
                                modifier = Modifier.size(20.dp).graphicsLayer(rotationZ = 270f))
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Row(modifier = Modifier.fillMaxWidth(0.9f)
                            , horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                            Text("О приложении", fontSize = 15.sp)
                            Image(painter = painterResource(Res.drawable.down_arrow),contentDescription = null,
                                modifier = Modifier.size(20.dp).graphicsLayer(rotationZ = 270f))
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth()
                    .fillMaxHeight(0.2f).background(Color.White))
            Column(modifier = Modifier.align(Alignment.BottomCenter), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth(0.9f)
                ) {
                    Text(text = "Выйти из профиля")
                }
                Spacer(modifier = Modifier.height(10.dp))

                Box() {
                    MenuBottomBar(Section.PROFILE).Content()
                }
            }

        }
    }
}