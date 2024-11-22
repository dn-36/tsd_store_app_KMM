package com.example.notes_screens_impl.screens.create_notes.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.network.notes_network.model.User
import org.example.project.presentation.crm_feature.create_notes_screen.viewmodel.CreateNotesIntents
import org.example.project.presentation.crm_feature.create_notes_screen.viewmodel.CreateNotesViewModel
import org.example.project.presentation.crm_feature.edit_note_screen.viewmodel.EditNoteViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class CreateNoteComponent ( override val viewModel: CreateNotesViewModel ) : NetworkComponent {

        var usersNoteCreated = mutableStateListOf<User>()

        @Composable
        override fun Component() {

            val scope = rememberCoroutineScope()

            viewModel.processIntent(CreateNotesIntents.GetAllUsersList(scope))

            Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.back), contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { viewModel.processIntent(CreateNotesIntents.Back) }
                        )
                        Text(
                            "Новая заметка",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 40.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    OutlinedTextField(
                        value = viewModel.createNotesState.name,
                        onValueChange = {
                            viewModel.createNotesState = viewModel.createNotesState.copy(
                                name = it
                            )
                        },
                        label = { Text("Название") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp) // Стандартная высота TextField
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.createNotesState.status,
                        onValueChange = {
                            viewModel.createNotesState = viewModel.createNotesState.copy(
                                status = it
                            )
                        },
                        label = { Text("Статус") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.createNotesState = viewModel.createNotesState.copy(
                                        expandedStatus = !viewModel.createNotesState.expandedStatus
                                    )
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
                            .heightIn(min = 40.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { }// Стандартная высота TextField
                    )
                    if (viewModel.createNotesState.expandedStatus) {
                        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.2f)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(listOf("Активна", "Скрыта")) { index, item ->
                                    Text(item,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })
                                            {
                                                viewModel.createNotesState = viewModel.createNotesState.copy(
                                                    status = item
                                                )
                                                viewModel.createNotesState = viewModel.createNotesState.copy(
                                                    expandedStatus = false
                                                )
                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.createNotesState.users,
                        onValueChange = {inputText ->
                            viewModel.createNotesState = viewModel.createNotesState.copy(
                                users = inputText,
                                filteredUsers = viewModel.createNotesState.listAllUsers.filter {
                                    it.name!!.contains(inputText, ignoreCase = true)
                                }
                            )
                        },
                        label = { Text("Пользователи") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.createNotesState = viewModel.createNotesState.copy(
                                        expandedUsers = !viewModel.createNotesState.expandedUsers
                                    )
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
                            .heightIn(min = 40.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { }// Стандартная высота TextField
                    )
                    if (viewModel.createNotesState.expandedUsers) {
                        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(viewModel.createNotesState.filteredUsers) { index, item ->
                                    Text(item.name!!,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                usersNoteCreated.add(item)

                                                println("$usersNoteCreated")

                                                viewModel.createNotesState = viewModel.createNotesState.copy(

                                                    expandedUsers = false,

                                                    usersNoteCreated = usersNoteCreated

                                                )

                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyColumn (modifier = Modifier.fillMaxWidth()) {
                        items(usersNoteCreated){ item ->
                            Box(modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))){
                                Text(text = item.name!!,color = Color.White, fontSize = 15.sp, modifier = Modifier.padding(8.dp).align(
                                    Alignment.CenterStart))
                                Image(painter = painterResource(Res.drawable.cancel),contentDescription = null,
                                    modifier = Modifier.padding(8.dp).size(10.dp).align(Alignment.TopEnd).clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    {
                                        viewModel.processIntent(CreateNotesIntents.DeleteUserNote(item))
                                        usersNoteCreated.remove(item)})
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.createNotesState.description,
                        onValueChange = {
                            viewModel.createNotesState = viewModel.createNotesState.copy(
                                description = it
                            )
                        },
                        label = { Text("Описание") },
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(0.3f) // Стандартная высота TextField
                    )

                    Spacer(modifier = Modifier.height(10.dp))


                }
                Row(
                    modifier = Modifier.fillMaxWidth(0.95f)
                        .align(Alignment.BottomCenter).padding(bottom = 16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { viewModel.processIntent(CreateNotesIntents.Cancel) },
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.45f)
                    ) {
                        Text(text = "Отменить")
                    }
                    Button(
                        onClick = { viewModel.processIntent(CreateNotesIntents.Next(scope))
                            usersNoteCreated = mutableStateListOf()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.9f)
                    ) {
                        Text(text = "Продолжить")
                    }
                }
            }
        }
}