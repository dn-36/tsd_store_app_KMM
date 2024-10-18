package com.example.notes_screens_impl.screens.notes.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar

import org.example.project.presentation.crm_feature.notes_screen.model.Notes
import org.example.project.presentation.crm_feature.notes_screen.util.formatDateTime
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents
import com.example.notes_screens_impl.screens.notes.viewmodel.NotesViewModel
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus

class NotesScreen:Screen{
    val vm = NotesViewModel()
    @Composable
    override fun Content() {

        val scope = rememberCoroutineScope()

        vm.processIntent(NotesIntents.SetNotes(scope) )
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Заметки", color = Color.Black, fontSize = 20.sp)
               // Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    if(vm.notesState.listNotes.size != 0) {
                        itemsIndexed(vm.notesState.listNotes.chunked(2)) { index, items ->
                            Row(
                                modifier = Modifier.padding(top = 10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                items.forEachIndexed { index, item ->
                                    Notes.Content(item.name!!,{ vm.processIntent(NotesIntents.EditNote(item))},
                                        formatDateTime( item.updatedAt!!))
                                }
                                if (items.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }

                        }
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End, modifier = Modifier.align(Alignment.BottomCenter)) {
                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.padding(16.dp).size(70.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { vm.processIntent(NotesIntents.CreateBookmarks) })
                Box(modifier = Modifier) {
                    MenuBottomBar().Content(MenuBottomBarSection.CRM)
                }
            }

        }
    }
}