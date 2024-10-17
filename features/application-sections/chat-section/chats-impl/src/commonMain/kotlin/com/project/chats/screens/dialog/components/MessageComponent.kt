package com.project.chats.screens.dialog.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.user_chats


@Composable
fun MessageComponent(message: Message) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp), // отступ от правого края
            horizontalArrangement = when (message.whoseMessage) {
                WhoseMessage.YOU -> Arrangement.End
                WhoseMessage.INTERLOCUTOR -> Arrangement.Start
            }
        ) {
            if(message.whoseMessage == WhoseMessage.INTERLOCUTOR) {
                Column(modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically)) {
                    if (!message.urlIcon.isNullOrBlank()) {
                        CoilImage(
                            imageModel = { message.urlIcon },
                            modifier = Modifier.padding(3.dp).size(30.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.user_chats),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Text(message.name, fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
            if (message.whoseMessage == WhoseMessage.YOU) {
                Text(
                    message.time,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(4.dp),
                    fontSize = 9.sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFD2F0B1))
                    .padding(10.dp), // Padding для внутреннего контента
                contentAlignment = Alignment.CenterEnd
            ) {
                    Text(
                        message.text,
                        fontSize = 15.sp
                    )

            }

            if (message.whoseMessage == WhoseMessage.INTERLOCUTOR) {
                Text(
                    message.time,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .padding(4.dp),
                    fontSize = 9.sp
                )
            }
            if(message.whoseMessage == WhoseMessage.YOU) {
                Column(modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically)) {
                    if (!message.urlIcon.isNullOrBlank()) {
                        CoilImage(
                            imageModel = { message.urlIcon },
                            modifier = Modifier.padding(3.dp).size(30.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.user_chats),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Text("You", fontSize = 10.sp, modifier = Modifier.align(Alignment.CenterHorizontally))
                }
            }
        }
    }
}


