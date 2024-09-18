package org.example.project.presentation.core.app.ui

import androidx.compose.runtime.*
import org.example.project.presentation.core.Navigator
import org.example.project.presentation.core.app.viewmodel.AppEvent
import org.example.project.presentation.core.app.viewmodel.AppViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.ui.EnteringAnumberScreen
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui.QRCodeMenu
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin


object App {

    private val viewModel = AppViewModel(getKoin().get())

    @Composable
    @Preview
    fun content() {
        val state by viewModel.state.collectAsState()

            viewModel.proccesIntent(AppEvent.InitScreenEvent)
        if (state.is_was_authorization) {
            Navigator.Content( QRCodeMenu)
        } else {
            Navigator.Content(EnteringAnumberScreen)
        }
    }
}

