package org.example.project

import androidx.compose.ui.interop.LocalUIViewController
import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.presentation.core.app.ui.App
import org.example.project.presentation.core.initKoin



fun MainViewController() = ComposeUIViewController {

    initKoin()
    App.content()
}

//UINavigationController