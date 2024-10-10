package com.project

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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.project.menu.screen.OrganizationScreenApi
import com.project.chats.ChatScreensApi
import com.project.chats.ProfileScreensApi
import com.project.network.Navigation
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.`menu-crm-api`.MenuCrmScreenApi
import com.project.menu.screen.TapeScreenApi
import org.koin.mp.KoinPlatform.getKoin

class OrganizationScreen(): Screen {
    @Composable
    override fun Content() {
        val organizationScreens : OrganizationScreenApi = getKoin().get()
        val chatsScreens : ChatScreensApi = getKoin().get()
        val menuCrmScreens : MenuCrmScreenApi = getKoin().get()
        val tapeScreens : TapeScreenApi = getKoin().get()
        val profileScreens : ProfileScreensApi = getKoin().get()
        Navigation.navigator = LocalNavigator.currentOrThrow
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Организации", color = Color.Black, fontSize = 20.sp)
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                MenuBottomBar().init(
                    menuCrmScreens.MenuCrm(),
                    profileScreens.profile() ,
                    organizationScreens.organization(),
                    chatsScreens.chatsScreen(),
                    tapeScreens.tape(),
                ) .Content()
            }
        }
    }
}