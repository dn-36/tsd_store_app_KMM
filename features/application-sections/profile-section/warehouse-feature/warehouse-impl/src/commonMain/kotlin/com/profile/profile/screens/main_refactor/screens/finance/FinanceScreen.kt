package com.profile.profile.screens.profile_screen.screens.finance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.jetbrains.compose.resources.painterResource


class FinanceScreen: Screen {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Приход Расход", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                MenuBottomBarWarehouse().Content()
            }
        }
    }
}