package org.example.project.core.menu_bottom_bar.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseIntents
import com.profile.profile.screens.warehouse_feature.core.menu_bottom_bar_warehouse.viewmodel.MenuBottomBarWarehouseViewModel
import com.project.core_app.menu_bottom_bar.viewmodel.MenuBottomBarIntents
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection


import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.exchange
import project.core.resources.printing
import project.core.resources.user
import project.core.resources.warehouse

class MenuBottomBarWarehouse {

val vm = MenuBottomBarWarehouseViewModel()

    private companion object{
        var _warehouseScreen: Screen? = null
        var _profileScreen: Screen? = null
        var _printScreen: Screen? = null
        var _financeScreen: Screen? = null
    }

    fun init(
        warehouseScreen: Screen,
        profileScreen: Screen,
        financeScreen: Screen,
        printScreen: Screen,
    ): MenuBottomBarWarehouse {
        _warehouseScreen = warehouseScreen
        _profileScreen = profileScreen
        _printScreen = printScreen
        _financeScreen = financeScreen
        return this
    }

    @Composable
     fun Content(section: MenuBottomBarWarehouseSection) {

        vm.processIntent(MenuBottomBarWarehouseIntents.SetScreen(section))

        Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxHeight(0.1f)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Row(modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceBetween){
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(color = vm.state.section.WarehouseButtonCollor)
                            .width(70.dp).height(40.dp).clickable { vm.processIntent(MenuBottomBarWarehouseIntents.Warehouse(
                                _warehouseScreen!!)) }){
                        Image(painter = painterResource(Res.drawable.warehouse),contentDescription = null,
                            modifier =  Modifier.size(30.dp).align(Alignment.Center))
                        }
                        Text("Склад", color = Color.Black, fontSize = 12.sp)

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(color = vm.state.section.FinanceButtonCollor)
                            .width(70.dp).height(40.dp).clickable { vm.processIntent(MenuBottomBarWarehouseIntents.Finance(
                                _financeScreen!!))}){
                            Image(painter = painterResource(Res.drawable.exchange),contentDescription = null,
                                modifier =  Modifier.size(30.dp).align(Alignment.Center))
                        }
                        Text("Приход Расход", color = Color.Black, fontSize = 12.sp)

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(color = vm.state.section.PrintButtonCollor)
                            .width(70.dp).height(40.dp).clickable { vm.processIntent(MenuBottomBarWarehouseIntents.Print(
                                _printScreen!!)) }){
                            Image(painter = painterResource(Res.drawable.printing),contentDescription = null,
                                modifier =  Modifier.size(30.dp).align(Alignment.Center))
                        }
                        Text("Напечатать", color = Color.Black, fontSize = 12.sp)

                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(50.dp)).background(Color.White)
                            .width(70.dp).height(40.dp).clickable { vm.processIntent(MenuBottomBarWarehouseIntents.Profile(
                                _profileScreen!!)) }){
                            Image(painter = painterResource(Res.drawable.user),contentDescription = null,
                                modifier =  Modifier.size(30.dp).align(Alignment.Center))
                        }
                        Text("Профиль", color = Color.Black, fontSize = 12.sp)

                    }

                }
            }
    }
}