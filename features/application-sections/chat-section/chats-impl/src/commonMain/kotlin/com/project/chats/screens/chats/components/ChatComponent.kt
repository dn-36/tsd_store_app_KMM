package com.project.chats.screens.chats.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.chats.screens.chats.viewmodel.ChatsIntents
import com.project.chats.screens.chats.viewmodel.ChatsViewModel
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.core_app.network_base_screen.NetworkComponent
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus

class ChatComponent(override val viewModel: ChatsViewModel) : NetworkComponent {


    @Composable
    override fun Component() {
//dscdscd
        val scope = rememberCoroutineScope()
        val state by this.viewModel.state.collectAsState()
        this.viewModel.processIntent(ChatsIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.align(Alignment.TopCenter).padding(16.dp)) {
                Text("Чаты", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.listchats,  key = { it.uiChat }) { item ->
                        ChatItem(
                            item.urlIconChat,
                            item.title,
                            item.lastMassage,
                            "${item.timeEndDate.time} ${item.timeEndDate.date}",
                            {
                                this@ChatComponent.viewModel.processIntent(
                                    ChatsIntents.DialogueSelection(scope, item.uiChat,
                                        item.urlIconChat, item.title,item.countNewMessage)
                                )
                            },
                            { this@ChatComponent.viewModel.processIntent(ChatsIntents.ShowDeleteDialog(item.uiChat)) }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Image(
                    painter = painterResource(Res.drawable.plus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.End)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() })
                        { this@ChatComponent.viewModel.processIntent(ChatsIntents.AddChat) }
                )

                MenuBottomBar().Content(MenuBottomBarSection.CHATS)
            }
        }
        if(state.isShowDeleteDialog) {
            ConfirmDeleteChatDialog(
                { this.viewModel.processIntent(ChatsIntents.DeleteChat(scope))},
                { this.viewModel.processIntent(ChatsIntents.CancelDeleteDialog)})
        }

    }

}