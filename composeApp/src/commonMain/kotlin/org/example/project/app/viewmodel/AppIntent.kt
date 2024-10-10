package org.example.project.app.viewmodel

sealed class AppIntent {
    object SetScreenIntent : AppIntent()
}