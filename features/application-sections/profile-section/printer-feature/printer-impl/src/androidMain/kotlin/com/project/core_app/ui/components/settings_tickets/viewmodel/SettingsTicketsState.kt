package com.project.core_app.ui.components.settings_tickets.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class SettingsTicketsState(
    val x:Int = 0,
    val y:Int = 0,
    val heightTicketMM:Int = 50,
    val widthTicketMM:Int = 50
)