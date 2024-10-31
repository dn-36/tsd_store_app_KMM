package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.app.ui.App

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App.AppContent()
}