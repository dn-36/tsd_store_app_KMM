package org.example.project.presentation.core.app.viewmodel

import androidx.compose.runtime.Composable

sealed class AppIntent {
    object SetScreenIntent : AppIntent()
}