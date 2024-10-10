package org.example.project.presentation.crm_feature.edit_note_screen.screen

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.network.notes_network.model.NoteResponse
import org.example.project.presentation.crm_feature.edit_note_screen.model.WindowUpdate
import org.example.project.presentation.crm_feature.edit_note_screen.viewmodel.EditNoteIntents
import org.example.project.presentation.crm_feature.edit_note_screen.viewmodel.EditNoteViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.dots

data class EditNoteScreen(val noteResponse: NoteResponse) : Screen {
    val vm = EditNoteViewModel()
    @Composable
    override fun Content(){

        val scope = rememberCoroutineScope()

        vm.processIntent(EditNoteIntents.SetScreen(noteResponse,scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                , horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(Res.drawable.back), contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            {
                                vm.processIntent(
                                    EditNoteIntents.UpdateNoteBack(
                                        noteResponse, scope)
                                )
                               // vm.processIntent(EditNoteIntents.Back)
                            }
                        )
                        Text(
                            "редактирование заметки",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                    Image(painter = painterResource(Res.drawable.dots), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            vm.editNoteState = vm.editNoteState.copy(
                                expandedSettings = !vm.editNoteState.expandedSettings
                            )
                        })
                }


                /* Spacer(modifier = Modifier.height(10.dp))
               Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.05f).background(Color.Black)){
                    LazyRow { itemsIndexed(listOf(Res.drawable.bold,Res.drawable.italic
                    ,Res.drawable.strikethrough,Res.drawable.underline,Res.drawable.h1,Res.drawable.h2,
                        Res.drawable.h3,Res.drawable.h4,Res.drawable.h4,Res.drawable.h5
                    ,Res.drawable.txt_color,Res.drawable.bg_color,Res.drawable.indent,Res.drawable.outdent
                    ,Res.drawable.justify_left,Res.drawable.justify_center,Res.drawable.justify_right
                    ,Res.drawable.bullets,Res.drawable.numbers,Res.drawable.blockquote
                    ,Res.drawable.insert_image,Res.drawable.file,
                        Res.drawable.insert_link)){index, item ->
                       Image(painter = painterResource(item),contentDescription = null,
                           modifier = Modifier.padding(8.dp).size(20.dp))
                    }
                    }
                }*/
                Spacer(modifier = Modifier.height(30.dp))
                BasicTextField(value = vm.editNoteState.noteText!!,
                   onValueChange = {vm.editNoteState = vm.editNoteState.copy(
                       noteText = it
                   ) },
                    textStyle = TextStyle(fontSize = 15.sp)
                )
            }
            if(vm.editNoteState.expandedSettings) {
                Box(modifier = Modifier.fillMaxWidth().padding(top = 50.dp,end = 16.dp)) {
                    Box(modifier = Modifier.align(Alignment.CenterEnd)
                       .fillMaxWidth(0.55f).fillMaxHeight(vm.editNoteState.heightBox)
                    ) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        Column {
                                Text("смена статуса",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(10.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })
                                        {
                                            vm.editNoteState = vm.editNoteState.copy(
                                                expandedSettings = false
                                            )
                                            vm.processIntent(EditNoteIntents.SelectingEditableCategory(0))
                                        }
                                )
                            if(vm.editNoteState.creator) {
                                Text("смена пользователей",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(10.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })
                                        {
                                            vm.editNoteState = vm.editNoteState.copy(
                                                expandedSettings = false
                                            )
                                            vm.processIntent(
                                                EditNoteIntents.SelectingEditableCategory(
                                                    1
                                                )
                                            )
                                        }
                                )
                            }
                            Text("смена названия",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    {
                                        vm.editNoteState = vm.editNoteState.copy(
                                            expandedSettings = false
                                        )
                                        vm.processIntent(EditNoteIntents.SelectingEditableCategory(2))
                                    }
                            )
                            Text("удалить заметку",
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    {
                                        vm.editNoteState = vm.editNoteState.copy(
                                            expandedSettings = false
                                        )
                                        vm.processIntent(EditNoteIntents.SelectingEditableCategory(3))
                                    })
                            }
                        }
                    }
                }

                if (vm.editNoteState.openWindowUpdate) {
                    println("проверка видимости")
                    println("проверка видимости")
                    println("проверка видимости")
                    println("${vm.editNoteState.openWindowUpdate},${vm.editNoteState.statusTF}")
                    println("проверка видимости")
                    println("проверка видимости")
                    println("проверка видимости")
                    when (vm.editNoteState.categoryNow) {
                        0 -> {
                           WindowUpdate(vm,noteResponse).Status()
                        }

                        1 -> {
                            WindowUpdate(vm,noteResponse).Users()
                        }

                        2 -> {
                            WindowUpdate(vm,noteResponse).Name()
                        }

                        3 -> {
                            WindowUpdate(vm,noteResponse).Delete()
                        }
                    }
                }
            }
        }
    }
