package org.example.project.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.project.menu.screen.OrganizationScreenApi
import com.project.network.Navigation
import com.project.`outhorization-screen-api`.AuthorizationScreensApi
import org.example.project.app.domain.AuthorizationStatus
import org.example.project.app.viewmodel.AppIntent
import org.example.project.app.viewmodel.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatform.getKoin


object App {

    private val viewModel = AppViewModel(getKoin().get())

    @Composable
    @Preview
    fun Content() {
        val state by viewModel.state.collectAsState()
        val authorization:AuthorizationScreensApi = getKoin().get()
        val organizationScreen: OrganizationScreenApi = getKoin().get()

        viewModel.proccesIntent(AppIntent.SetScreenIntent)
        when (state.authorizationStatus) {
            AuthorizationStatus.LOADING -> {
                Box(modifier = Modifier.fillMaxSize()) {

                   // CircularProgressIndicator(modifier = Modifier.size(40.dp).align(Alignment.Center))
                }
            }

            AuthorizationStatus.WAS_NO_AUTHORIZATION -> {
                Navigation.navigator(authorization.enteringNumber())
            }

            AuthorizationStatus.WAS_AUTHORIZATION -> {
                Navigation.navigator( organizationScreen.organization())
            }

        }
    }
}

