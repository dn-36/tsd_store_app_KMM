package org.example.project.presentation.core

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

//import cafe.adriel.voyager.navigator.Navigator

object Navigator {
        lateinit var navigator: Navigator

    @Composable
    fun Content(firstScreen: Screen) {
        Navigator(screen = firstScreen)
    }
}