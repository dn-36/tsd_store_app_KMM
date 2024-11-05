package com.project.chats.screens.select_contact.screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.screens.select_contact.viewmodel.SelectContactsIntents
import com.project.chats.screens.select_contact.viewmodel.SelectContactsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.ready
import project.core.resources.user_chats
import kotlin.jvm.Transient


class SelectContactsScreen : Screen {

   @Transient private val vm:SelectContactsViewModel = getKoin().get()

    @Composable
    override fun Content() {
    val scope = rememberCoroutineScope()
       vm.processIntent(SelectContactsIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize())
        {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                TextField(
                    value = vm.state.text,
                    onValueChange = { textInput ->
                                    vm.state = vm.state.copy(
                                        text = textInput,
                                        selectedListContacts = vm.state.selectedListContacts.filter {
                                            it.name.contains(textInput, ignoreCase = true)
                                        }.toSet()
                                    )},
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                vm.processIntent(SelectContactsIntents.ClearingTypedText)
                            }
                        ) {

                            Icon(
                                painter = painterResource(Res.drawable.cancel),
                                contentDescription = "Отмена",
                                modifier = Modifier.size(15.dp)
                            )

                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.95f),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLabelColor = Color.Transparent
                    ),
                    textStyle = TextStyle(fontSize = 15.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.Center).height(1.dp)
                            .background(Color.LightGray)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            vm.processIntent(SelectContactsIntents.ColorButtonAll(scope))
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.45f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = vm.state.colorButtonAll
                        )
                    ) {
                        Text(text = "Телефон")
                    }
                    Button(
                        onClick = {
                           vm.processIntent(SelectContactsIntents.ColorButtonOrganization(scope))
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.9f),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = vm.state.colorButtonOrganization,
                        )
                    ) {
                        Text(text = "Организация")
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                if(vm.state.selectedListContacts.size != 0) {
                    LazyRow(modifier = Modifier.fillMaxWidth()) {
                        items(vm.state.selectedListContacts.toList()) { item ->
                            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
                                    Image(
                                        painter = painterResource(Res.drawable.cancel),
                                        contentDescription = null,
                                        modifier = Modifier.size(10.dp).align(Alignment.TopEnd)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })
                                            {

                                                vm.processIntent(
                                                    SelectContactsIntents.CanselContact(
                                                        item
                                                    )
                                                )

                                            }
                                    )

                                Column(modifier = Modifier.align(Alignment.Center)) {
                                    Image(
                                        painter = painterResource(Res.drawable.user_chats),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Spacer(modifier = Modifier.height(5.dp))

                                            Text(text = item.name, // Отображаем текст item
                                                fontSize = 15.sp,
                                                modifier = Modifier.width(50.dp), // Ограничиваем ширину до 50dp
                                                textAlign = TextAlign.Center, // Центрируем текст по горизонтали
                                                maxLines = 1, // Ограничиваем до одной строки
                                                overflow = TextOverflow.Ellipsis)

                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                LazyColumn {
                    items(vm.state.listContacts  ) { item ->
                        Row(
                            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(0.95f).clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            {

                            vm.processIntent(SelectContactsIntents.SelectContact(item))

                            },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(50.dp)) {
                                Image(
                                    painter = painterResource(Res.drawable.user_chats),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp).align(Alignment.Center)
                                )
                                if(vm.state.selectedListContacts.find { it == item } != null) {
                                    Image(
                                        painter = painterResource(Res.drawable.ready),
                                        contentDescription = null,
                                        modifier = Modifier.size(20.dp).align(Alignment.BottomEnd)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(15.dp))
                            Column(
                                modifier = Modifier.fillMaxHeight(0.1f),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("${item.name}", fontSize = 17.sp)
                                Text(
                                    "${item.number }",
                                    fontSize = 12.sp,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth().padding(25.dp).align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {

                Image(painterResource(Res.drawable.back), contentDescription = null,
                    modifier = Modifier.padding(10.dp).size(30.dp).clickable(
                        indication = null, // Отключение эффекта затемнения
                        interactionSource = remember { MutableInteractionSource() })
                    { vm.processIntent(SelectContactsIntents.Back) })

                Image(
                    painter = painterResource(Res.drawable.ready), contentDescription = null,
                    modifier = Modifier.size(60.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { vm.processIntent(SelectContactsIntents.Next) }
                )
            }
        }
    }
}