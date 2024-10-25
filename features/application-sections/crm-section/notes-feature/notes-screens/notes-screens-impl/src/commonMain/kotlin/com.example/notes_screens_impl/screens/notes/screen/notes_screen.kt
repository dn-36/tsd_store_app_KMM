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
import com.example.notes_screens_impl.screens.notes.component.NotesComponent
import com.project.core_app.menu_bottom_bar.ui.MenuBottomBar
import org.example.project.presentation.crm_feature.notes_screen.model.Notes
import org.example.project.presentation.crm_feature.notes_screen.util.formatDateTime
import org.example.project.presentation.crm_feature.notes_screen.viewmodel.NotesIntents
import com.example.notes_screens_impl.screens.notes.viewmodel.NotesViewModel
import com.project.core_app.network_base_screen.NetworkScreen
import org.example.project.core.menu_bottom_bar.viewmodel.MenuBottomBarSection
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus
import org.koin.mp.KoinPlatform.getKoin

class NotesScreen:NetworkScreen(
    NotesComponent(getKoin().get())
)