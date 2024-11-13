package com.project.chats.screens.dialog.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffectScope
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.chats.screens.dialog.domain.models.Message
import com.project.chats.screens.dialog.domain.models.StatusMessage
import com.project.chats.screens.dialog.domain.models.WhoseMessage
import com.skydoves.landscapist.coil3.CoilImage
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.circle
import project.core.resources.error
import project.core.resources.icon_circle
import project.core.resources.is_loading
import project.core.resources.is_readed
import project.core.resources.is_seccuess
import project.core.resources.share
import project.core.resources.user_chats


@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MessageComponent(
    message: Message,
    resendMessage:()->Unit,
    onSwipeRight:()->Unit,
    onSelectMessage:(ui:String)->Unit,
    showPhoto:(photos:List<String>)->Unit
    ) {

    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart) {
                onSwipeRight()
            }
            false
        }
    )
        val imageSize by animateDpAsState(
            targetValue = if (dismissState.dismissDirection == DismissDirection.EndToStart) 22.dp else 0.dp
            )

    Box(
        modifier = Modifier
                .pointerInput(message.isShowSelectedMessage) {
                    detectTapGestures(

                        onTap = {
                            if (message.isShowSelectedMessage ) {
                                onSelectMessage(message.ui)
                            }
                            },
                    /*    onPress = {
                            if (message.isShowSelectedMessage ) {
                                onSelectMessage(message.ui)
                            }
                        },*/
                       onLongPress = {

                            onSelectMessage(message.ui)
                       
                        },
                    )
                }


            .fillMaxWidth()
            .padding(vertical = 10.dp,)
    ) {

        SwipeToDismiss(
            state = dismissState,
            directions =
                setOf(DismissDirection.EndToStart),

            background = {

                val color = when (dismissState.dismissDirection) {
                    DismissDirection.EndToStart -> Color.Transparent
                    else -> Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color),
                    contentAlignment = Alignment.CenterEnd
                ) {
                        Image(
                            modifier = Modifier.padding(end = 9.dp, bottom = 6.dp).size(imageSize),
                            painter = painterResource(Res.drawable.share),
                            contentDescription = null
                        )
                }
            },
            dismissContent = {
          Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp), // отступ от правого края
            horizontalArrangement = when (message.whoseMessage) {
                WhoseMessage.YOU -> Arrangement.End
                WhoseMessage.INTERLOCUTOR -> Arrangement.Start
            }
        ){
            if (message.whoseMessage == WhoseMessage.INTERLOCUTOR) {
                Column(
                    modifier = Modifier
                        .padding(
                            start = if(message.isShowSelectedMessage ) 35.dp
                            else 0.dp
                        )
                        .align(Alignment.CenterVertically)
                ) {
                    if (false){//!message.url_icon.isNullOrBlank()) {
                        CoilImage(
                            imageModel = { message.url_icon },
                            modifier = Modifier.padding(3.dp).size(20.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.user_chats),
                            contentDescription = null,
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            ) .size(30.dp)
                        )
                    }
                    Text(
                        text = message.name,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
            Row (
                    modifier = Modifier.weight(1f),
            horizontalArrangement =   if(message.whoseMessage == WhoseMessage.YOU)
                Arrangement.End
            else
                Arrangement.Start
            ) {
                if (message.whoseMessage == WhoseMessage.YOU) {
                    Text(
                        message.date,
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(4.dp)
                            .wrapContentWidth(),
                        fontSize = 9.sp
                    )
                    when(message.statusMessage){

                        StatusMessage.IS_ERROR ->  Image(
                             painter = painterResource(Res.drawable.error),
                             modifier = Modifier
                                 .padding(5.dp)
                                 .size(20.dp)
                                 .align(Alignment.CenterVertically)
                                 .clickable {
                                     resendMessage()
                                 },
                            contentDescription =  null
                         )
                         else -> {}
                    }
                }

                Box(
                    modifier = Modifier

                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD2F0B1))
                        .padding(10.dp)
                        ,
                ) {
                    Column {
                    if(message.sendImage != null)    {
                        Box(
                            modifier = Modifier
                                .size(150.dp)
                        ){
                            CircularProgressIndicator(modifier =
                            Modifier
                                .size(20.dp)
                                .align(Alignment.Center))
                        }
                    }else{
                   if(message.url_icon != null) {
                       CoilImage(
                           imageModel = { message.url_icon },
                           loading = { CircularProgressIndicator(modifier = Modifier.size(5.dp)) },
                           modifier = Modifier.size(150.dp).clickable {
                               showPhoto(listOf(message.url_icon!!))
                           }
                       )
                   }
                        }
                   if(message.answerMessage != null) {
                           Column (
                           modifier = Modifier
                           .padding(end = 10.dp)
                           .background(Color(0xFFD2F0FF))
                               .combinedClickable(
                                   onClick = {},
                                   onLongClick = {
                                       onSelectMessage(message.ui)
                                   }
                               )
                           ) {

                               Text(
                                   message.answerMessage?.name?:"" ,
                                   fontSize = 15.sp,
                                   modifier = Modifier.padding(end = 15.dp),
                                   fontWeight = FontWeight.Bold,

                               )
                               Text(
                                   message.answerMessage?.text?:"",
                                   fontSize = 12.sp,
                                   modifier = Modifier.padding(end = 15.dp)
                               )

                           }
                       }


                        Box {
                            Text(

                                message.text,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(end = 15.dp)
                            )
                            if (message.whoseMessage == WhoseMessage.YOU) {
                                when (message.statusMessage) {
                                    StatusMessage.IS_LOADING -> {
                                        Image(
                                            modifier = Modifier
                                                .padding(start = 5.dp)
                                                .align(Alignment.BottomEnd)
                                                .size(10.dp),
                                            painter = painterResource(
                                                Res.drawable.is_loading
                                            ),
                                            contentDescription = null
                                        )
                                    }

                                    StatusMessage.IS_SECCUESS -> {
                                        Image(
                                            modifier = Modifier
                                                .padding(start = 5.dp)
                                                .align(Alignment.BottomEnd)
                                                .size(10.dp),
                                            painter = painterResource(
                                                Res.drawable.is_seccuess
                                            ),
                                            contentDescription = null
                                        )
                                    }

                                    StatusMessage.IS_READED -> {
                                        Image(
                                            modifier = Modifier
                                                .padding(start = 5.dp)
                                                .align(Alignment.BottomEnd)
                                                .size(10.dp),
                                            painter = painterResource(
                                                Res.drawable.is_readed
                                            ),
                                            contentDescription = null
                                        )
                                    }

                                    else -> {}
                                }
                            }
                        }
                    }
                }

                if (message.whoseMessage == WhoseMessage.INTERLOCUTOR) {
                    Text(
                        message.date,
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(4.dp)
                            .wrapContentWidth(),
                        fontSize = 9.sp
                    )
                }
            }
            if (message.whoseMessage == WhoseMessage.YOU) {
                Column(modifier = Modifier.padding(4.dp).align(Alignment.CenterVertically)) {
                    if (false){
                        CoilImage(
                            imageModel = { message.url_icon },
                            modifier = Modifier.padding(3.dp).size(30.dp)
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.user_chats),
                            contentDescription = null,
                            modifier = Modifier.padding(
                                start = 10.dp,
                                end = 10.dp,
                                top = 5.dp,
                                bottom = 5.dp
                            ).size(30.dp)
                        )
                    }
                    Text("You", fontSize = 10.sp, modifier =
                    Modifier
                        .align(Alignment.CenterHorizontally))
                }
            }

        }
    }
        )
if(message.isShowSelectedMessage) {
    Image(
        painter = painterResource(
            if(message.isSelectedMessage)
            Res.drawable.icon_circle else
            Res.drawable.circle),
        modifier = Modifier
            .padding(5.dp)
            .size(20.dp)
            .align(Alignment.CenterStart),
        contentDescription = null
    )
}
}
}
