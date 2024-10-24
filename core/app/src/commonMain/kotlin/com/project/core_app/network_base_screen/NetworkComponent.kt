package com.project.core_app.network_base_screen

import androidx.compose.runtime.Composable

interface NetworkComponent {
    val viewmodel:NetworkViewModel
    @Composable
    fun Component()
}