package com.project.`menu-crm-impl`

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.project.network.Navigation
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.`menu-crm-api`.viewmodel.MenuViewModel

import org.example.project.presentation.menu_feature.viewmodel.MenuIntents


class MenuCrmScreen:Screen{
    val vm = MenuViewModel()
    @Composable
    override fun Content() {
        Navigation.navigator =  LocalNavigator.currentOrThrow

        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("CRM", color = Color.Black, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { vm.processIntent(MenuIntents.ClickedBookmarks) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()

                ) {
                    Text(text = "Заметки", modifier = Modifier)
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "CRM")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Контроль проектов")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Открыть меню")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Календарь")
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {  },
                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Share log file")
                }
                Spacer(modifier = Modifier.height(20.dp))

            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                MenuBottomBar().Content()
            }
        }
    }
}