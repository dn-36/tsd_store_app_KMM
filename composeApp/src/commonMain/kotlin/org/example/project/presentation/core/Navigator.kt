package org.example.project.presentation.core

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import org.example.project.presentation.feature.authorization.screens.entering_number.ui.EnteringAnumberScreen

//import cafe.adriel.voyager.navigator.Navigator

object NavigatorView {
        lateinit var navigator: Navigator


    @Composable
    fun Content() {
        Navigator(screen = EnteringAnumberScreen)
    }
}