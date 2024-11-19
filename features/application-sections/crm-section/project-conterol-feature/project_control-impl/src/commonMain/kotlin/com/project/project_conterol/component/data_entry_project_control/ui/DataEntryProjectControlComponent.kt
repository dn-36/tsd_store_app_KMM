package com.project.project_conterol.component.data_entry_project_control.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.utils.boxHeight
import com.project.project_conterol.component.calendar.CustomCalendar
import com.project.project_conterol.component.data_entry_project_control.viewmodel.DataEntryProjectControlIntents
import com.project.project_conterol.component.data_entry_project_control.viewmodel.DataEntryViewModelProjectControl
import com.project.project_conterol.model.ProjectResponseModel
import com.project.project_conterol.model.ServiceModel
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryProjectControlComponent (

    val listAllProjects: List<ProjectResponseModel>,

    val item: ServiceModel?,

    val onClickBack:() -> Unit,

    val onClickCreate:( coroutineScope: CoroutineScope, text:String,

                       data: String, time: String,

                       project_id: String) -> Unit,

    val onClickUpdate:( id: Int, coroutineScope: CoroutineScope, text:String,

                        data: String, time: String,

                        project_id: String) -> Unit

) {

    val viewModel = DataEntryViewModelProjectControl()

    @Composable

    fun Content () {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(DataEntryProjectControlIntents.SetScreen( listAllProjects, item ))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)

            .clickable(indication = null, // Отключение эффекта затемнения

                interactionSource = remember { MutableInteractionSource() }) {  }) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    if ( item == null ) {

                        Text("Создание", color = Color.Black, fontSize = 20.sp)

                    }

                    else {

                        Text("Редактирование", color = Color.Black, fontSize = 20.sp)

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(

                        value = viewModel.state.projects,

                        onValueChange = { inputText ->

                            viewModel.processIntents(

                                DataEntryProjectControlIntents.InputTextProject(inputText,

                                    listAllProjects.filter {

                                        it.name.contains(inputText, ignoreCase = true)
                                    }))

                        },

                        label = { Text("Проект") },

                        textStyle = TextStyle(fontSize = 17.sp),

                        trailingIcon = {

                            IconButton(

                                onClick = {

                                    viewModel.processIntents(DataEntryProjectControlIntents.MenuProject)

                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.down_arrow),
                                    contentDescription = "Поиск",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 45.dp)

                    )

                    if (viewModel.state.selectedProject != null) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                        ) {

                            Text(
                                text = viewModel.state.selectedProject!!.name,
                                color = Color.White,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(8.dp).align(
                                    Alignment.CenterStart
                                )
                            )

                            Image(painter = painterResource(Res.drawable.cancel),
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp).size(10.dp)
                                    .align(Alignment.TopEnd)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })

                                    {

                                        viewModel.processIntents(

                                            DataEntryProjectControlIntents.DeleteSelectedProject
                                        )

                                    })
                        }
                    }

                    if (viewModel.state.expendedProject) {

                        Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                            viewModel.state.filteredListProjects.size).dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(viewModel.state.filteredListProjects)

                                { index, item ->

                                    if (item.name != "") {

                                        Text(item.name,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f)
                                                .padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение затемнения
                                                    interactionSource = remember {

                                                        MutableInteractionSource() })

                                                {

                                                    viewModel.processIntents(

                                                        DataEntryProjectControlIntents.SelectProject(

                                                            item
                                                        )
                                                    )

                                                })

                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(

                        value = "${viewModel.state.date?:""}",

                        onValueChange = {

                        },

                        label = { Text("Дата") },

                        textStyle = TextStyle(fontSize = 17.sp),

                        trailingIcon = {

                            IconButton(

                                onClick = {

                                    viewModel.processIntents(DataEntryProjectControlIntents.OpenCloseCalendar)

                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.down_arrow),
                                    contentDescription = "Поиск",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 45.dp)

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Box( modifier = Modifier.fillMaxWidth().height(55.dp).background(Color.White)

                        .clickable( indication = null, // Отключение эффекта затемнения

                        interactionSource = remember { MutableInteractionSource() })

                    { viewModel.processIntents(DataEntryProjectControlIntents.MenuTime) }

                        .border( width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(5.dp))

                        , contentAlignment = Alignment.CenterStart

                    ) {

                        Row ( modifier = Modifier

                            .fillMaxWidth(0.35f).padding( horizontal = 16.dp),

                            horizontalArrangement = Arrangement.SpaceBetween,

                            verticalAlignment = Alignment.CenterVertically) {

                            Text(text = viewModel.state.hour, fontSize = 20.sp)

                        Text(text = ":", fontSize = 20.sp)

                            Text(text = viewModel.state.minutes, fontSize = 20.sp)
                    }

                }

                if ( viewModel.state.expendedTime ) {

                    Box(modifier = Modifier.fillMaxWidth(0.35f).height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}

                        Row ( modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement = Arrangement.SpaceBetween) {

                            LazyColumn {

                                itemsIndexed (

                                    List(24) { index -> index.toString().padStart(

                                        2, '0') }

                                )

                                { index, item ->

                                        Text( text = item,
                                            fontSize = 20.sp,
                                            modifier = Modifier
                                                .padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение затемнения
                                                    interactionSource = remember {

                                                        MutableInteractionSource() })

                                                { viewModel.processIntents(

                                                    DataEntryProjectControlIntents.SelectHour(item)) })

                                }
                            }

                            LazyColumn {

                                itemsIndexed(

                                    List(60) { index -> index.toString().padStart(

                                        2, '0') }
                                )

                                { index, item ->

                                    Text( text = item,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            { viewModel.processIntents(

                                                DataEntryProjectControlIntents.SelectMinutes(item)) })

                                }
                            }
                        }
                    }
                }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(

                        value = viewModel.state.description,

                        onValueChange = {

                            viewModel.processIntents(DataEntryProjectControlIntents.InputTextDescription(it))

                        },

                        label = { Text("Описание") },

                        textStyle = TextStyle(fontSize = 17.sp),

                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 45.dp)

                    )

                if ( !viewModel.state.isVisibilityCalendar ) {

                    Spacer(modifier = Modifier.height(50.dp))

                    if ( item == null ) {

                        Button(

                            onClick = {

                                if (viewModel.state.selectedProject != null) {

                                    onClickCreate(

                                        scope,
                                        viewModel.state.description,
                                        "${viewModel.state.date ?: ""}",

                                        "${viewModel.state.hour}:${viewModel.state.minutes}",

                                        "${viewModel.state.selectedProject!!.id}"

                                    )
                                }
                            },

                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()

                        ) {

                            Text(text = "Создать")

                        }
                    }

                    else {

                        Button(

                            onClick = {

                                if (viewModel.state.selectedProject != null) {

                                    onClickUpdate (

                                        item.id?:0,

                                        scope,

                                        viewModel.state.description,

                                        "${viewModel.state.date ?: ""}",

                                        "${viewModel.state.hour}:${viewModel.state.minutes}",

                                        "${viewModel.state.selectedProject!!.id}"

                                    )
                                }
                            },

                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()

                        ) {

                            Text(text = "Редактировать")

                        }

                    }

                }

                if ( viewModel.state.isVisibilityCalendar ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box () {

                        CustomCalendar {

                            viewModel.state = viewModel.state.copy(

                                date = it

                            )

                        }

                    }

                }

            }

        }
    }
}