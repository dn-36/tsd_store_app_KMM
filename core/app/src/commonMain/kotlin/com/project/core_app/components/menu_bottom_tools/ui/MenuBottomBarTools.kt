package com.project.core_app.components.menu_bottom_tools.ui

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
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsSection
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsViewModel
import com.project.core_app.components.menu_bottom_tools.viewmodel.MenuBottomBarToolsIntents
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.ip_camera
import project.core.resources.printing

class MenuBottomBarTools {

private val vm = MenuBottomBarToolsViewModel()

    private companion object{
        var _printScreen: Screen? = null
    }

    fun init(

        printScreen: Screen,
    ): MenuBottomBarTools {

        _printScreen = printScreen
         return this
    }

    @Composable
     fun Content(section: MenuBottomBarToolsSection) {

        vm.processIntent(MenuBottomBarToolsIntents.SetScreen(section))

        Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
            Box(modifier = Modifier.background(Color.Black) .height(50.dp).width(1.dp).align(Alignment.Center),)
                Row(modifier = Modifier.align(Alignment.BottomCenter)
                    .fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceBetween){

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp).weight(1F)
                    ) {
                        Box(modifier = Modifier.clip(
                            RoundedCornerShape(10.dp))
                            .background(color = vm.state.section.PrintButtonCollor)
                            .width(100.dp).height(60.dp).clickable { vm.processIntent(
                                MenuBottomBarToolsIntents.Print)
                            }){
                            Image(painter = painterResource(Res.drawable.printing),contentDescription = null,
                                modifier =  Modifier.size(40.dp).align(Alignment.Center))
                        }
                        Text("Напечатать", color = Color.Black, fontSize = 12.sp)

                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp).weight(1F)
                        ) {
                        Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))
                            .background(color = vm.state.section.ToolsButtonCollor)
                            .width(100.dp).height(60.dp).clickable {
                                vm.processIntent(
                                MenuBottomBarToolsIntents.Tools
                                )
                            }){
                            Image(painter = painterResource(Res.drawable.ip_camera),contentDescription = null,
                                modifier =  Modifier.size(50.dp).align(Alignment.Center))
                        }
                        Text("видеонаблюдения", color = Color.Black, fontSize = 12.sp)

                    }

                }
            }
    }
}
