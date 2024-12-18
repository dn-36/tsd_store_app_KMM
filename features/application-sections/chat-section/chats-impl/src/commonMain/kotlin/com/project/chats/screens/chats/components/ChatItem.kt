package com.project.chats.screens.chats.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.bin
import project.core.resources.user_chats


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChatItem(
    urlIconChat:String?,
    titleChat:String,
    lastMassage:String,
    countNewMessage:Int?,
    timeLastMessage:String,
    onClickDialogue:() -> Unit,
    onSwipeRight:() -> Unit
){


        // Создаем состояние для DismissValue
        val dismissState = rememberDismissState(
            confirmStateChange = {
                if (it == DismissValue.DismissedToStart) {
                    onSwipeRight()
                }
                false
            }
        )

        SwipeToDismiss(
            state = dismissState,
            directions = setOf(DismissDirection.EndToStart),
            background = {
                // Фон при свайпе (появляется при удалении)
                val color = when (dismissState.dismissDirection) {
                    DismissDirection.EndToStart -> Color.Transparent//Color.Red
                    else -> Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color)
                        .padding(16.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(Res.drawable.bin),
                        contentDescription = null

                    )
                }
            },
            dismissContent = {
                // Контент элемента списка
                Row(modifier = Modifier.fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 10.dp).clickable{ onClickDialogue() },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically){
                    if(!urlIconChat.isNullOrBlank()) {
                        CoilImage(
                            imageModel = { urlIconChat },
                            loading = { CircularProgressIndicator(modifier = Modifier.size(10.dp)) },
                            modifier = Modifier.size(50.dp)
                        )
                    }else{
                        Image(painter = painterResource(Res.drawable.user_chats),contentDescription = null,
                            modifier = Modifier.size(50.dp))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        modifier = Modifier.height(60.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(titleChat, fontSize = 18.sp)
                            if(countNewMessage != null && countNewMessage != 0 ) {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 10.dp)
                                        .background(Color.Blue, shape = RoundedCornerShape(8.dp))
                                ) {
                                    Text(
                                        countNewMessage.toString(),
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .align(Alignment.Center),
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                lastMassage,
                                fontSize = 13.sp,
                                color = Color.LightGray
                            )
                            Text(
                                timeLastMessage,
                                modifier = Modifier.padding(end = 10.dp),
                                fontSize = 13.sp,
                                color = Color.LightGray
                            )
                        }
                        Box(modifier = Modifier.fillMaxWidth().height(1.dp)
                            .background(Color.LightGray))

                    }
                }

            }
        )
    }

