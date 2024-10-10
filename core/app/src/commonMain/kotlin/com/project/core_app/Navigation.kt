package com.project.network

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator

object Navigation {
    lateinit var navigator: Navigator
    @Composable
    fun navigator(screen: Screen) {
        Navigator(screen = screen)
    }
}


