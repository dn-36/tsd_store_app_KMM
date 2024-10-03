package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.core.models.ProductPresentationModel


actual class QRCodeMenuScreen : Screen {
    //}
    actual var product: ProductPresentationModel
        get() = ProductPresentationModel()
        set(value) {}
     @Composable
    override fun Content() {

    }
}