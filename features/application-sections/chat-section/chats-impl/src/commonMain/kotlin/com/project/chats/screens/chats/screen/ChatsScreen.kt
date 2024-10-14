package com.project.chats.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.model.listDialogItem
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import org.example.project.nika_screens_chats.list_dialog_feature.viewmodel.ListDialogIntents
import org.example.project.nika_screens_chats.list_dialog_feature.viewmodel.ListDialogViewModel

import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus

class ChatsScreen : Screen {

    private val vm = ListDialogViewModel()

    @Composable
    override fun Content() {

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

            Column(modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)) {
                Text("Чаты", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(listOf("")) { item ->
                        listDialogItem(vm)
                    }
                }
            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {

                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.padding(16.dp).size(60.dp).clickable(
                        indication = null, // Отключение эффекта затемнения
                        interactionSource = remember { MutableInteractionSource() })
                    { vm.processIntent(ListDialogIntents.AddChat) })

                Box(modifier = Modifier) {
                    MenuBottomBar().Content()
                }
            }
        }
    }
}