package org.example.project.presentation.core.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.example.project.presentation.core.NavigatorComponent
import org.example.project.presentation.core.app.domain.AuthorizationStatus
import org.example.project.presentation.core.app.viewmodel.AppIntent
import org.example.project.presentation.core.app.viewmodel.AppViewModel
import org.example.project.presentation.feature.authorization.screens.entering_number.ui.EnteringAnumberScreen
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.QRCodeMenuScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin


object App {

    private val viewModel = AppViewModel(getKoin().get())

    @Composable
    @Preview
    fun content() {
        val state by viewModel.state.collectAsState()

        viewModel.proccesIntent(AppIntent.SetScreenIntent)
        when (state.authorizationStatus) {
            AuthorizationStatus.LOADING -> {
                Box(modifier = Modifier.fillMaxSize()) {

                   // CircularProgressIndicator(modifier = Modifier.size(40.dp).align(Alignment.Center))
                }
            }

            AuthorizationStatus.WAS_NO_AUTHORIZATION -> {
                NavigatorComponent.Content(EnteringAnumberScreen)
            }

            AuthorizationStatus.WAS_AUTHORIZATION -> {
                NavigatorComponent.Content(QRCodeMenuScreen())
            }

        }
    }
}

