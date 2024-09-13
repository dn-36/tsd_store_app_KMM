package org.example.project

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.module.core.AuthorizationClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.project.presentation.core.NavigatorView

@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigatorView.Content()

    }
}