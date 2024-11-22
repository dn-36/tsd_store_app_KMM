package com.project.core_app.components.menu_bottom_bar_contragents.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsIntents
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsSection
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsViewModel

import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.clock
import project.core.resources.contractor
import project.core.resources.location
import project.core.resources.location_sign
import project.core.resources.projects

class MenuBottomBarContragents {

val vm = MenuBottomBarContragentsViewModel()

    private companion object{

        var _contragentsScreen: Screen? = null

        var _locationsScreen: Screen? = null

    }

    fun init(

        contragentsScreen: Screen,

        locationsScreen: Screen,

    ): MenuBottomBarContragents {

        _contragentsScreen = contragentsScreen

        _locationsScreen = locationsScreen

        return this
    }

    @Composable
     fun Content(section: MenuBottomBarContragentsSection) {

        vm.processIntent(MenuBottomBarContragentsIntents.SetScreen(section))

        Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

            Row(modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceAround){

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.state.section.ContragentsButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarContragentsIntents.Contragents(
                                    _contragentsScreen!!))
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.contractor), contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )
                    }
                    Text("Контрагенты", color = Color.Black, fontSize = 12.sp,

                        maxLines = 1, // Ограничение текста в одну строку

                        overflow = TextOverflow.Ellipsis ) // Добавление троеточия, если текст не помещается))

                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.state.section.LocationsButtonCollor)
                        .width(70.dp).height(40.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarContragentsIntents.Locations(
                                    _locationsScreen!!
                                )
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.location_sign),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp).align(Alignment.Center)
                        )
                    }
                    Text(
                        "Локации", color = Color.Black, fontSize = 12.sp,

                        maxLines = 1, // Ограничение текста в одну строку
                        overflow = TextOverflow.Ellipsis
                    ) // Добавление троеточия, если текст не помещается)

                }
            }
        }
    }
}
