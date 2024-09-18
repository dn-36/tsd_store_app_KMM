package org.example.project.presentation.core.app.viewmodel

sealed class AppEvent {
    object InitScreenEvent : AppEvent()
}