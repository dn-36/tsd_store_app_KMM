package com.project.core_app.components.menu_bottom_bar.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

import com.project.core_app.components.menu_bottom_bar.viewmodel.MenuBottomBarIntents
import com.project.core_app.components.menu_bottom_bar.viewmodel.MenuBottomBarViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection


import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.home
import project.core.resources.icon_youtub
import project.core.resources.menu
import project.core.resources.messenger
import project.core.resources.play_video
import project.core.resources.squares_stack
import project.core.resources.user

class MenuBottomBar {
    val vm = MenuBottomBarViewModel(getKoin().get())

    private companion object {
        var _crmScreen: Screen? = null
        var _profileScreen: Screen? = null
        var _homeScreen: Screen? = null
        var _chatsScreen: Screen? = null
        var _tapeScreen: Screen? = null

    }

    fun init(
        menuScreen: Screen,
        profileScreen: Screen,
        homeScreen: Screen,
        chatsScreen: Screen,
        tapeScreen: Screen,
    ): MenuBottomBar {
        _crmScreen = menuScreen
        _profileScreen = profileScreen
        _homeScreen = homeScreen
        _chatsScreen = chatsScreen
        _tapeScreen = tapeScreen
        return this
    }


    @Composable
    fun Content(section: MenuBottomBarSection) {
        val scope = rememberCoroutineScope()
        vm.processIntent(MenuBottomBarIntents.SetScreen(section,scope))
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxHeight(0.15f)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.menuBottomBarState.section.OrganizationButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarIntents.Home(_homeScreen!!)
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.home), contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )
                    }
                    Text("Организа...", color = Color.Black, fontSize = 12.sp)

                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.menuBottomBarState.section.CrmButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarIntents.CRM(_crmScreen!!)
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.squares_stack),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )
                    }
                    Text("CRM", color = Color.Black, fontSize = 12.sp)

                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.menuBottomBarState.section.TapeButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarIntents.Tape(_tapeScreen!!)
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.icon_youtub), contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )
                    }
                    Text("Лента", color = Color.Black, fontSize = 12.sp)

                }
                Box {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                        Box(modifier = Modifier.clip(RoundedCornerShape(20.dp))
                            .background(color = vm.menuBottomBarState.section.ChutsButtonCollor)
                            .width(70.dp).height(40.dp).clickable {
                                vm.processIntent(
                                    MenuBottomBarIntents.Chats(_chatsScreen!!)
                                )
                            }) {
                            Image(
                                painter = painterResource(Res.drawable.messenger),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp).align(Alignment.Center)
                            )
                        }
                        Text(
                            "Чаты", color = Color.Black, fontSize = 12.sp,
                            //    modifier = Modifier.padding(bottom = 50.dp))
                        )

                    }
                    if (vm.menuBottomBarState.countNewMessage != 0) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 9.dp, end = 9.dp)
                                .background(
                                    Color.Blue,
                                    shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            Text(
                                vm.menuBottomBarState.countNewMessage.toString(),
                                fontSize = 10.sp,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .align(Alignment.Center),
                                color = Color.White
                            )
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.menuBottomBarState.section.ProfileButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarIntents.Profile(_profileScreen!!)
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.menu), contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )

                    }
                    Text("еще", color = Color.Black, fontSize = 12.sp)

                }
            }
        }
    }
}
