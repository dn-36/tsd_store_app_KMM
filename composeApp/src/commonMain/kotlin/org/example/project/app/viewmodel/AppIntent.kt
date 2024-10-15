package org.example.project.app.viewmodel

import kotlinx.coroutines.CoroutineScope

sealed class AppIntent {
    data class SetScreenIntent(val scope:CoroutineScope) : AppIntent()
}