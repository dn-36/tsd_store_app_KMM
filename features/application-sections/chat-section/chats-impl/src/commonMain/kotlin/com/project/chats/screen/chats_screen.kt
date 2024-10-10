package com.project.chats.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.model.ChatsItem

import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus

object ChatsScreena: Screen {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)){
            Column(modifier = Modifier.padding(16.dp),) {
                Text("Чаты", color = Color.Black, fontSize = 20.sp)
                LazyColumn {
                        itemsIndexed(listOf("")) { index, items ->
                            ChatsItem.Content()

                        }
                }
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {
                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.padding(16.dp).size(70.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { })
                Box(modifier = Modifier) {
                    com.project.core_app.menu_bottom_bar.ui.MenuBottomBar().Content()
                }
            }
        }
    }
}