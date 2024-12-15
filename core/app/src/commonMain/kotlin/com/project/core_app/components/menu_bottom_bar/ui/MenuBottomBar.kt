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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.jetbrains.compose.resources.DrawableResource


import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.home
import project.core.resources.icon_youtub
import project.core.resources.menu
import project.core.resources.messenger
import project.core.resources.squares_stack

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
        vm.processIntent(MenuBottomBarIntents.SetScreen(section, scope))

        Column(
            modifier = Modifier
           //     .padding(bottom = 16.dp)
                .fillMaxWidth()
                .background(Color.White)
        ) {
           // Spacer(Modifier.fillMaxHeight(0.03F))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.95f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Элементы нижней панели
                MenuButton(
                    color = vm.menuBottomBarState.section.OrganizationButtonCollor,
                    text = "Организа...",
                    iconRes = Res.drawable.home
                ) {
                    vm.processIntent(MenuBottomBarIntents.Home(_homeScreen!!))
                }
                MenuButton(
                    color = vm.menuBottomBarState.section.CrmButtonCollor,
                    text = "CRM",
                    iconRes = Res.drawable.squares_stack
                ) {
                    vm.processIntent(MenuBottomBarIntents.CRM(_crmScreen!!))
                }
                MenuButton(
                    color = vm.menuBottomBarState.section.TapeButtonCollor,
                    text = "Лента",
                    iconRes = Res.drawable.icon_youtub
                ) {
                    vm.processIntent(MenuBottomBarIntents.Tape(_tapeScreen!!))
                }
                Box {
                    MenuButton(
                        color = vm.menuBottomBarState.section.ChutsButtonCollor,
                        text = "Чаты",
                        iconRes = Res.drawable.messenger
                    ) {
                        vm.processIntent(MenuBottomBarIntents.Chats(_chatsScreen!!))
                    }
                    if (vm.menuBottomBarState.countNewMessage != 0) {
                        // Индикатор новых сообщений
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(top = 5.dp, end = 5.dp)
                                .size(20.dp)
                                .background(Color.Blue, shape = RoundedCornerShape(50.dp))
                        ) {
                            Text(
                                text = vm.menuBottomBarState.countNewMessage.toString(),
                                fontSize = 10.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                MenuButton(
                    color = vm.menuBottomBarState.section.ProfileButtonCollor,
                    text = "еще",
                    iconRes = Res.drawable.menu
                ) {
                    vm.processIntent(MenuBottomBarIntents.Profile(_profileScreen!!))
                }
            }
            Spacer(Modifier.fillMaxHeight(0.03F))
        }
    }

    @Composable
    fun MenuButton(
        color: Color,
        text: String,
        iconRes: DrawableResource,
        onClick: () -> Unit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Пропорциональное расстояние между кнопками
         //   Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(color)
                    .size(56.dp) // Пропорциональный размер кнопки
                    .clickable { onClick() }
            ) {
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.6f) // Размер иконки пропорционален размеру кнопки
                        .align(Alignment.Center)
                )
            }
            Text(
                text = text,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}