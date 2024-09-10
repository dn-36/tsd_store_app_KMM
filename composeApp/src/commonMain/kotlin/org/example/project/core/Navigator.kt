package org.example.project.core

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
//import cafe.adriel.voyager.navigator.Navigator
import org.tsdstore.project.feature.authorization.presentation.screens.entering_number.EnteringAnumberScreen

object NavigatorView {
    lateinit var navigator: Navigator

    @Composable
    fun Content() {
        Navigator(screen = EnteringAnumberScreen)
    }
}