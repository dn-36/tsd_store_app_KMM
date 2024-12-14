package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui



import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.udpPlayer.core.ProductPresentationModel
import kotlin.jvm.Transient

expect class QRCodeMenuScreen() : Screen {
    var product: ProductPresentationModel
   }