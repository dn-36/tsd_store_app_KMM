package com.project.core_app.network_base_screen

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

interface NetworkComponent {
    val networkViewModel:NetworkViewModel
    @Composable
    fun Component()
}