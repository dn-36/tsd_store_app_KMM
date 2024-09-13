package org.example.project.presentation.feature.qr_code_main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import cafe.adriel.voyager.core.screen.Screen

class QRCodeMenu(): Screen {
    @Composable
    override fun Content() {
       Text(text = "QRCodeMenu()" )
    }
}