package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import tsdstorekmm.composeapp.generated.resources.Res
import tsdstorekmm.composeapp.generated.resources.st

object QRcodeSizeComponent {
    @Composable
    fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxHeight().fillMaxWidth().alpha(0.6f)
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier.clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.5f).fillMaxWidth()
                    .align(Alignment.BottomCenter).background(Color.White)
            ) {
                var fontSize by remember { mutableStateOf(16f) }
                var imageSize by remember { mutableStateOf(20f) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Изменяемый текст",
                        fontSize = fontSize.sp,  // Применяемый размер шрифта
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Slider(
                        value = fontSize,
                        onValueChange = { newSize -> fontSize = newSize },
                        valueRange = 12f..40f,  // Диапазон изменения шрифта
                        modifier = Modifier.fillMaxWidth()
                    )

                    Image(
                        painter = painterResource(Res.drawable.st),
                        contentDescription = null,
                        modifier = Modifier.height(imageSize.dp).width(150.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Slider(
                        value = imageSize,
                        onValueChange = { newSize -> imageSize = newSize },
                        valueRange = 12f..40f,  // Диапазон изменения шрифта
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f)
                    .background(Color.White).align(BottomCenter)
            )

        }
    }
}

