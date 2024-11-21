package com.project.project_conterol.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.components.menu_bottom_bar_projects.ui.MenuBottomBarProjects
import com.project.core_app.components.menu_bottom_bar_projects.viewmodel.MenuBottomBarProjectsSection
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.core_app.utils.boxHeight
import com.project.`menu-crm-api`.ProjectControlScreenApi
import com.project.project_conterol.component.data_entry_project_control.ui.DataEntryProjectControlComponent
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection
import org.example.project.presentation.project_control.viewmodel.ProjectControlIntents
import org.example.project.presentation.project_control.viewmodel.ProjectControlViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.dots
import project.core.resources.plus
import project.core.resources.update_pencil

class ProjectControlComponent ( override val viewModel: ProjectControlViewModel

   ) : NetworkComponent {

    @Composable
    override fun Component() {

        val projectsControlScreen: ProjectControlScreenApi = KoinPlatform.getKoin().get()

        val scope = rememberCoroutineScope()

        viewModel.processIntents(ProjectControlIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        {
                            if ( !viewModel.state.isVisibilityDeleteComponent ) {

                               // viewModel.processIntents(ProjectControlIntents.Back)

                            }

                            }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Контроль проектов", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Для поиска введите название проекта", color = Color.Gray,

                    fontSize = 16.sp)

                Spacer(modifier = Modifier.height(8.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    ProjectControlIntents.InputTextSearchComponent(text)) } ).Content()

                if ( viewModel.state.listProjectsControl != null ) {

                    Text(
                        "Общая сумма  ${viewModel.state.listProjectsControl!!.balans?:0}",

                        color = Color.Black, fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn {

                        itemsIndexed (

                            viewModel.state.listFilteredProjectsControl!!.data?: emptyList() )

                        { index, item ->

                            Box () {

                                Column(modifier = Modifier.fillMaxWidth().clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {
                                    viewModel.processIntents(

                                        ProjectControlIntents.OpenDescription(index))

                                }) {

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        "Проект : ${item.project?.name?: "не указан"}", fontSize = 16.sp,

                                        modifier = Modifier.fillMaxWidth(0.75f)
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        "Пользователь : ${item.user?.name?:"не указан"}", fontSize = 16.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        "Дата : ${item.data}",

                                        fontSize = 16.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Затраченное время : ${item.time}", fontSize = 16.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Сумма : ${item.price}", fontSize = 16.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    if (viewModel.state.listExpendedDescription[index]) {

                                        Spacer(modifier = Modifier.height(8.dp))

                                        Text(
                                            text = "Описание: ${item.text ?: ""}", fontSize = 18.sp,

                                            fontWeight = FontWeight.Bold
                                        )

                                        Spacer(modifier = Modifier.height(8.dp))

                                    }

                                    Box(
                                        modifier = Modifier.height(1.dp).fillMaxWidth()

                                            .background(Color.Gray)
                                    )


                                }

                                Column ( modifier = Modifier.align(Alignment.TopEnd)

                                    .padding(8.dp), horizontalAlignment = Alignment.End) {

                                    Image(painterResource(Res.drawable.dots),
                                        contentDescription = null,

                                        modifier = Modifier.size(25.dp).clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource()
                                                })

                                            {
                                                viewModel.processIntents(

                                                    ProjectControlIntents.Dots(index)
                                                )
                                            })

                                    if (viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index]
                                    ) {

                                        Spacer(modifier = Modifier.height(15.dp))

                                        Box(modifier = Modifier.fillMaxWidth(0.4f).height(80.dp)) {
                                            Card(
                                                modifier = Modifier.fillMaxSize()
                                                    .shadow(
                                                        elevation = 8.dp,
                                                        shape = RoundedCornerShape(8.dp)
                                                    ),
                                                backgroundColor = Color.White,
                                                shape = RoundedCornerShape(8.dp)
                                            ) {}

                                            Column ( modifier = Modifier.padding(10.dp) ) {

                                                Text("Обновить", fontSize = 16.sp,

                                                    modifier = Modifier.clickable(
                                                        indication = null, // Отключение затемнения
                                                        interactionSource = remember {

                                                            MutableInteractionSource()
                                                        })

                                                    {
                                                        if (!viewModel.state.isVisibilityDeleteComponent) {

                                                            viewModel.processIntents(

                                                                ProjectControlIntents

                                                                    .OpenUpdateDataEntryComponent(
                                                                        scope,
                                                                        item
                                                                    )
                                                            )
                                                        }
                                                    })

                                                Spacer(modifier = Modifier.height(20.dp))

                                                Text("Удалить", fontSize = 16.sp,

                                                    modifier = Modifier.clickable(
                                                        indication = null, // Отключение эффекта затемнения
                                                        interactionSource = remember {

                                                            MutableInteractionSource()
                                                        })

                                                    {
                                                        viewModel.processIntents(
                                                            ProjectControlIntents.OpenDeleteComponent(
                                                                item
                                                            )
                                                        )
                                                    })
                                            }

                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }


            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

                PlusButton {

                    if ( !viewModel.state.isVisibilityDeleteComponent ) {

                        viewModel.processIntents(
                            ProjectControlIntents.OpenCreateDataEntryComponent(
                                scope
                            )
                        )

                    }

                }

                MenuBottomBarProjects().init(

                   projectsControlScreen.projectControl(),

                    projectsControlScreen.projectControl()

                ).Content(MenuBottomBarProjectsSection.PROJECTS_CONTROL)
            }

        }

        if ( viewModel.state.isVisibilityDataEntryComponent ) {

            DataEntryProjectControlComponent( listAllProjects = viewModel.state.listProjects,

                item = viewModel.state.updatedItem,

                onClickBack = {

                    viewModel.processIntents(ProjectControlIntents.BackFromDataEntry) },

                onClickCreate = { scope, text, data, time, project_id ->

                    viewModel.processIntents(ProjectControlIntents.CreateProjectControl(scope,

                        text, data, time, project_id))

                }, onClickUpdate = { id, coroutineScope, text, data, time, project_id ->

                    viewModel.processIntents(ProjectControlIntents.UpdateProjectControl(

                        coroutineScope, id, text, data, time, project_id))

                }).Content()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent(

                name = "контроль пороекта",

                onClickDelete = { viewModel.processIntents(

                ProjectControlIntents.DeleteProjectControl(scope)) },

                onClickNo = { viewModel.processIntents(ProjectControlIntents.NoDelete) }).Content()

        }

    }
}