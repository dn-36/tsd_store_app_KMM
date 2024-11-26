package com.project.tape.component.data_entry.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.preat.peekaboo.image.picker.SelectionMode
import com.preat.peekaboo.image.picker.rememberImagePickerLauncher
import com.preat.peekaboo.image.picker.toImageBitmap
import com.project.core_app.utils.boxHeight
import com.project.tape.component.data_entry.viewmodel.DataEntryTapeIntents
import com.project.tape.component.data_entry.viewmodel.DataEntryTapeViewModel
import com.project.tape.model.ContragentsResponseModel
import com.project.tape.model.ProjectModel
import com.project.tape.model.ProjectResponseModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryTapeComponent (

    val onClickBack:() -> Unit,

    val listProjects: List<ProjectResponseModel>,

    val listContragents: List<ContragentsResponseModel>

    ) {

    val viewModel = DataEntryTapeViewModel()

    @Composable

    fun Content(){

        viewModel.processIntents(DataEntryTapeIntents.SetScreen( listProjects, listContragents))

        val scope = rememberCoroutineScope()

        val multipleImagePicker = rememberImagePickerLauncher(
            scope = scope,
            selectionMode = SelectionMode.Multiple(),
            onResult = { byteArrays ->

                viewModel.state = viewModel.state.copy(
                    image = byteArrays.map {
                        it.toImageBitmap()
                    }.get(0)
                )
            }
        )

        val scroll = rememberScrollState()

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp).verticalScroll(scroll)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Лента", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                    viewModel.processIntents(DataEntryTapeIntents.InputTextName(it))

                    },
                    label = { Text("Название") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp) // Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.description,

                    onValueChange = {

                    viewModel.processIntents(DataEntryTapeIntents.InputTextDescription(it))

                    },
                    label = { Text("Описание") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp) // Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.contragent,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryTapeIntents.InputTextContragent( inputText,

                                listContragents.filter {

                                    it.name!!.contains(inputText, ignoreCase = true)

                                } ))

                    },

                    label = { Text("Контрагент") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryTapeIntents.MenuContragents)

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

                if (viewModel.state.selectedContragent != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedContragent!!.name?:"Нет имени",
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

                                { viewModel.processIntents(

                                    DataEntryTapeIntents.DeleteSelectedContragent) })
                    }
                }

                if ( viewModel.state.expendedContragents ) {

                    Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                        viewModel.state.filteredListContragents.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.filteredListContragents)

                            { index, item ->


                                Text( item.name?:"Нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryTapeIntents.SelectContragent(item))

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.project,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryTapeIntents.InputTextProject( inputText,

                                listProjects.filter {

                                    it.name!!.contains(inputText, ignoreCase = true)

                                } ))

                    },

                    label = { Text("Проект") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryTapeIntents.MenuProjects)

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
                            text = viewModel.state.selectedProject!!.name?:"",
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

                                { viewModel.processIntents(

                                    DataEntryTapeIntents.DeleteSelectedProject) })
                    }
                }

                if ( viewModel.state.expendedProjects ) {

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

                                Text( item.name?:"Нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryTapeIntents.SelectProject(item))

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row (modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween ) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text("Добавить фото", color = Color.Black, fontSize = 18.sp,

                            modifier = Modifier.clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })

                            { multipleImagePicker.launch() }, textAlign = TextAlign.Center
                        )

                        if ( viewModel.state.image != null ) {

                            Spacer(modifier = Modifier.width(10.dp))

                            Image(painterResource(Res.drawable.cancel),contentDescription = null,

                                modifier = Modifier.size(15.dp). clickable(

                                    indication = null, // Отключение эффекта затемнения

                                    interactionSource = remember { MutableInteractionSource() })

                                { viewModel.processIntents(

                                    DataEntryTapeIntents.DeleteSelectedPhoto) })

                        }

                    }

                    Text("Добавить видео", color = Color.Black, fontSize = 18.sp,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(DataEntryTapeIntents.OpenMenuFiles) },

                        textAlign = TextAlign.Center)

                }

                Spacer(modifier = Modifier.height(10.dp))

                Row( modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceBetween ) {

                    if ( viewModel.state.image != null ) {

                        Box() {

                            Image(

                                modifier = Modifier.height(250.dp).fillMaxWidth(0.45f),

                                bitmap = viewModel.state.image!!, contentDescription = null,

                                contentScale = ContentScale.Crop
                            )

                        }

                    }

                }
            }
        }
    }
}