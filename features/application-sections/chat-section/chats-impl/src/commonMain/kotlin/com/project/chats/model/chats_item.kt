package com.project.chats.model

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.location

object ChatsItem{
    @Composable
    fun Content(){
        Box(modifier = Modifier.fillMaxWidth(0.95f)){
            Row(modifier = Modifier.fillMaxWidth()){
                Image(painter = painterResource(Res.drawable.location),contentDescription = null,
                    modifier = Modifier.size(50.dp))
                Column(modifier = Modifier.fillMaxHeight(0.2f),
                    verticalArrangement = Arrangement.SpaceBetween){
                 Text(text = "Название чата", fontSize = 17.sp)
                        Row() {
                            Text(
                                text = "последнее сообщение",
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                            Text(
                                text = "13:50",
                                fontSize = 13.sp,
                                color = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(5.dp))

                        Box(modifier = Modifier.fillMaxWidth(0.9f).height(1.dp)
                            .background(Color.Black))

                }
            }
        }
    }
}