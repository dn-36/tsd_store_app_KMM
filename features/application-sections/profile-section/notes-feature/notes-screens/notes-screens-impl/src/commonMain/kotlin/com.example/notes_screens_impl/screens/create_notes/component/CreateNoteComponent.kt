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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.project.core_app.network_base_screen.NetworkComponent
import com.project.core_app.utils.boxHeight
import com.project.network.notes_network.model.User
import org.example.project.presentation.crm_feature.create_notes_screen.viewmodel.CreateNotesIntents
import org.example.project.presentation.crm_feature.create_notes_screen.viewmodel.CreateNotesViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class CreateNoteComponent ( override val viewModel: CreateNotesViewModel ) : NetworkComponent {

        var usersNoteCreated = mutableStateListOf<User>()

        @Composable
        override fun Component() {

            val scope = rememberCoroutineScope()

            val scroll = rememberScrollState()

            viewModel.processIntent(CreateNotesIntents.GetAllUsersList(scope))

            Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

                Column(modifier = Modifier.fillMaxSize().padding(16.dp)

                    .verticalScroll(scroll)) {

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Image(
                            painter = painterResource(Res.drawable.back), contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })

                            { viewModel.processIntent(CreateNotesIntents.Back) }
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text("Новая заметка", color = Color.Black, fontSize = 20.sp)

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = viewModel.state.name,
                        onValueChange = {
                            viewModel.state = viewModel.state.copy(
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
                        value = viewModel.state.status,
                        onValueChange = {
                            viewModel.state = viewModel.state.copy(
                                status = it
                            )
                        },
                        label = { Text("Статус") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.state = viewModel.state.copy(
                                        expandedStatus = !viewModel.state.expandedStatus
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
                    if (viewModel.state.expandedStatus) {
                        Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
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
                                                viewModel.state = viewModel.state.copy(
                                                    status = item
                                                )
                                                viewModel.state = viewModel.state.copy(
                                                    expandedStatus = false
                                                )
                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = viewModel.state.users,
                        onValueChange = {inputText ->
                            viewModel.state = viewModel.state.copy(
                                users = inputText,
                                filteredUsers = viewModel.state.listAllUsers.filter {
                                    it.name!!.contains(inputText, ignoreCase = true)
                                }
                            )
                        },
                        label = { Text("Пользователи") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.state = viewModel.state.copy(
                                        expandedUsers = !viewModel.state.expandedUsers
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
                    if (viewModel.state.expandedUsers) {
                        Box(modifier = Modifier.fillMaxWidth().height(

                            boxHeight( viewModel.state.filteredUsers.size).dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(viewModel.state.filteredUsers) { index, item ->
                                    Text(item.name!!,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                usersNoteCreated.add(item)

                                                println("$usersNoteCreated")

                                                viewModel.state = viewModel.state.copy(

                                                    expandedUsers = false,

                                                    usersNoteCreated = usersNoteCreated

                                                )

                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyColumn (modifier = Modifier.fillMaxWidth().height(

                        (50 * usersNoteCreated.size).dp)) {

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
                        value = viewModel.state.description,
                        onValueChange = {
                            viewModel.state = viewModel.state.copy(
                                description = it
                            )
                        },
                        label = { Text("Описание") },
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxHeight(0.3f) // Стандартная высота TextField
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                            //.align(Alignment.BottomCenter).padding(bottom = 16.dp)
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
}