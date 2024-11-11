package com.project.project_conterol.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.PlusButton
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.project_conterol.component.data_entry.DataEntryComponent
import org.example.project.presentation.project_control.viewmodel.ProjectControlIntents
import org.example.project.presentation.project_control.viewmodel.ProjectControlViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil

class ProjectControlComponent ( override val viewModel: ProjectControlViewModel) : NetworkComponent {

    @Composable
    override fun Component() {

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

                        { viewModel.processIntents(ProjectControlIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Контроль проектов", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                if ( viewModel.state.listProjectsControl != null ) {

                    Text(
                        "Общая сумма  ${viewModel.state.listProjectsControl!!.balans?:0}",

                        color = Color.Black, fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn {

                        itemsIndexed ( viewModel.state.listProjectsControl!!.data?: emptyList() ) { index, item ->

                            Box () {
                                Column(modifier = Modifier.fillMaxWidth().clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {
                                    viewModel.processIntents(
                                        ProjectControlIntents.OpenDescription(
                                            index
                                        )
                                    )
                                }) {

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Text(
                                        "Проект : ${item.project?.name?: "не указан"}", fontSize = 16.sp,

                                        modifier = Modifier.fillMaxWidth(0.9f)
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

                                Row(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            { viewModel.processIntents(ProjectControlIntents

                                                .OpenUpdateDataEntryComponent(scope,item)) })


                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(painter = painterResource(Res.drawable.cancel),
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            { })

                                }

                            }

                        }
                    }

                }
            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                viewModel.processIntents(ProjectControlIntents.OpenCreateDataEntryComponent(scope))

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntryComponent.value ) {

            DataEntryComponent( listAllProjects = viewModel.state.listProjects,

                item = viewModel.state.updatedItem,

                onClickBack = {

                    viewModel.processIntents(ProjectControlIntents.BackFromDataEntry) },

                onClickCreate = { scope, text, data, time, project_id ->

                    viewModel.processIntents(ProjectControlIntents.CreateProjectControl(scope,

                        text, data, time, project_id))

                }).Content()

        }

    }
}