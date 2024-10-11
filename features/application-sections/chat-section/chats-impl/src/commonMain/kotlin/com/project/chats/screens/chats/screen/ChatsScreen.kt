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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.chats.screens.chats.component.listDialogItem
import com.project.network.chats_network.ChatsApi
import com.project.chats.screens.chats.viewmodel.ChatsIntents
import org.example.project.nika_screens_chats.list_dialog_feature.viewmodel.ChatsViewModel

import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.plus

class ChatsScreen : Screen {

    private val vm:ChatsViewModel = getKoin().get()

    @Composable
    override fun Content() {
      val scope = rememberCoroutineScope()
      val state by vm.state.collectAsState()
        vm.processIntent(ChatsIntents.SetScreen(scope))
        LaunchedEffect(true){
            println("-------list_chats-------")
           println("list_chats: "+ChatsApi().getChats().toString())
            println("-------list_chats-------")
        }

        Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {

            Column(modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)) {
                Text("Чаты", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.chats) { item ->
                        listDialogItem(
                            item.title,
                            item.lastMassage,
                            "nmhdo3jc-ht9v2nxa-08iv2hsd",
                            {vm.processIntent(ChatsIntents.DialogueSelection(scope,it))}
                        )
                    }
                }
            }
            Image(painter = painterResource(Res.drawable.plus),contentDescription = null,
                modifier = Modifier.padding(16.dp).size(60.dp).align(Alignment.BottomEnd).clickable(
                    indication = null, // Отключение эффекта затемнения
                    interactionSource = remember { MutableInteractionSource() })
                { vm.processIntent(ChatsIntents.AddChat) })
        }
    }
}