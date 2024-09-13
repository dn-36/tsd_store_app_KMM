package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.presentation.core.App
import org.example.project.presentation.core.initKoin


fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}