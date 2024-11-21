package com.project.core_app.components.menu_bottom_bar_projects.ui

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
import com.project.core_app.components.menu_bottom_bar_projects.viewmodel.MenuBottomBarProjectsIntents
import com.project.core_app.components.menu_bottom_bar_projects.viewmodel.MenuBottomBarProjectsSection
import com.project.core_app.components.menu_bottom_bar_projects.viewmodel.MenuBottomBarProjectsViewModel

import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.clock
import project.core.resources.exchange
import project.core.resources.projects
import project.core.resources.warehouse

class MenuBottomBarProjects {

val vm = MenuBottomBarProjectsViewModel()

    private companion object{

        var _projectsScreen: Screen? = null

        var _projectsControlScreen: Screen? = null

    }

    fun init(

        projectsScreen: Screen,

        projectsControlScreen: Screen,

    ): MenuBottomBarProjects {

        _projectsScreen = projectsScreen

        _projectsControlScreen = projectsControlScreen

        return this
    }

    @Composable
     fun Content(section: MenuBottomBarProjectsSection) {

        vm.processIntent(MenuBottomBarProjectsIntents.SetScreen(section))

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
                        .background(color = vm.state.section.ControlProjectsButtonCollor)
                        .width(80.dp).height(50.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarProjectsIntents.ProjectsControl(
                                    _projectsControlScreen!!))
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.clock), contentDescription = null,
                            modifier = Modifier.size(40.dp).align(Alignment.Center)
                        )
                    }
                    Text("Контроль проектов", color = Color.Black, fontSize = 12.sp,

                        maxLines = 1, // Ограничение текста в одну строку

                        overflow = TextOverflow.Ellipsis ) // Добавление троеточия, если текст не помещается))

                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15F))
                    Box(modifier = Modifier.clip(RoundedCornerShape(50.dp))
                        .background(color = vm.state.section.ProjectsButtonCollor)
                        .width(80.dp).height(50.dp).clickable {
                            vm.processIntent(
                                MenuBottomBarProjectsIntents.Projects(
                                    _projectsScreen!!
                                )
                            )
                        }) {
                        Image(
                            painter = painterResource(Res.drawable.projects),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp).align(Alignment.Center)
                        )
                    }
                    Text(
                        "Проекты", color = Color.Black, fontSize = 12.sp,

                        maxLines = 1, // Ограничение текста в одну строку
                        overflow = TextOverflow.Ellipsis
                    ) // Добавление троеточия, если текст не помещается)

                }
            }
        }
    }
}
