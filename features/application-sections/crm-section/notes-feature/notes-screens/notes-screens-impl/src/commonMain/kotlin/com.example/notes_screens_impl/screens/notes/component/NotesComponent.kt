package com.example.notes_screens_impl.screens.notes.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes_screens_impl.screens.notes.viewmodel.NotesViewModel
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import com.project.core_app.network_base_screen.NetworkComponent
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.example.project.presentation.crm_feature.notes_screen.model.Notes
import org.example.project.presentation.crm_feature.notes_screen.util.formatDateTime
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.plus

class NotesComponent ( override val viewModel: NotesViewModel ) :NetworkComponent {
    @Composable
    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntent(NotesIntents.SetNotes(scope) )

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntent(NotesIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Заметки", color = Color.Black, fontSize = 20.sp)

                }

                 Spacer(modifier = Modifier.height(20.dp))

                if (viewModel.notesState.listNotes.size != 0) {

                    LazyColumn(modifier = Modifier.fillMaxSize()) {

                        items(1) {

                            Row(
                                modifier = Modifier.padding(top = 10.dp)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Column() {

                                        val oddIndexedNotes =
                                            viewModel.notesState.listNotes.filterIndexed { index, _ -> index % 2 == 0 }

                                        for (item in oddIndexedNotes) {

                                            Notes.Content(
                                                item.name!!,
                                                item.text!!,
                                                { viewModel.processIntent(NotesIntents.EditNote(item)) },
                                                formatDateTime(item.updatedAt!!)
                                            )


                                        }

                                }
                                Column() {

                                        val oddIndexedNotes =
                                            viewModel.notesState.listNotes.filterIndexed { index, _ -> index % 2 != 0 }

                                        for (item in oddIndexedNotes) {

                                            Notes.Content(
                                                item.name!!,
                                                item.text!!,
                                                { viewModel.processIntent(NotesIntents.EditNote(item)) },
                                                formatDateTime(item.updatedAt!!)
                                            )


                                        }

                                }
                            }
                        }
                    }
                }
            }

            Box( modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
                .size(70.dp).clip(CircleShape).background(Color.White).border(

                    width = 2.dp, color = Color.Black, shape = CircleShape

                )
            , contentAlignment = Alignment.Center) {
                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.size(70.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { viewModel.processIntent(NotesIntents.CreateBookmarks) })
            }

        }
    }

}