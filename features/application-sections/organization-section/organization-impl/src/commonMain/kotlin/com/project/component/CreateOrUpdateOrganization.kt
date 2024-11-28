package com.project.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.project.network.organizations_network.model.Response
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.cancel

class CreateOrUpdateOrganization(

    val onClickCreate: (scope: CoroutineScope, name: String, url: String) -> Unit,

    val onClickUpdate: (scope: CoroutineScope, name: String, url: String) -> Unit,

    val onClickCansel: () -> Unit,

    val isUpdate: Boolean,

    val listBorderColor: List<Color>,

    val item: Response?

    ) : Screen {

    var name by mutableStateOf( if ( item != null ) item.company?.name else "" )

    var url by mutableStateOf( if ( item != null ) item.company?.url else "" )

    @Composable
    override fun Content() {

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    //.fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)//.fillMaxHeight(),
                   // verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        Spacer(modifier = Modifier.height(10.dp))

                        Row ( modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement = Arrangement.End ) {

                            Image(painter = painterResource(Res.drawable.cancel),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })

                                    { onClickCansel() })


                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = name!!,
                            onValueChange = {
                                name = it
                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = listBorderColor[0], // Цвет границы при фокусе
                                unfocusedBorderColor = listBorderColor[0], // Цвет границы в неактивном состоянии
                                backgroundColor = listBorderColor[0].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                            ),

                            label = { Text("Название") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 40.dp) // Стандартная высота TextField
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = url!!,
                            onValueChange = {
                                url = it
                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = listBorderColor[1], // Цвет границы при фокусе
                                unfocusedBorderColor = listBorderColor[1], // Цвет границы в неактивном состоянии
                                backgroundColor = listBorderColor[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                            ),

                            label = { Text("Url") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 40.dp) // Стандартная высота TextField
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    if (!isUpdate) {

                        Button(
                            onClick = { onClickCreate(scope, name!!, url!!) },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Создать")
                        }
                    } else {

                        Button(
                            onClick = { onClickUpdate(scope, name!!, url!!) },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Редактировать")
                        }

                    }

                    Spacer(modifier = Modifier.fillMaxHeight(0.07f))

                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ) {

            }

        }
    }
}