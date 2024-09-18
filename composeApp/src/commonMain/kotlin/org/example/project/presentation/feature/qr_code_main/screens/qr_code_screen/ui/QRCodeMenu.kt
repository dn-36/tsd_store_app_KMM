package org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.QRcodeMenuIntents
import org.example.project.presentation.feature.qr_code_main.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel
import org.jetbrains.compose.resources.painterResource
import tsdstorekmm.composeapp.generated.resources.Res
import tsdstorekmm.composeapp.generated.resources.barcode
import tsdstorekmm.composeapp.generated.resources.search
import tsdstorekmm.composeapp.generated.resources.settings

object QRCodeMenu: Screen {
    val viewModel = QRcodeMenuViewModel()
    @Composable
    override fun Content() {
        var barcodeInput by remember { mutableStateOf("") }
        var productInfo by remember { mutableStateOf("Product Name") }
        var selectedPrinter by remember { mutableStateOf("") }
        var printersExpanded by remember { mutableStateOf(false) }
        val printers = listOf("Категория 1", "Категория 2", "Категория 3") // Замените на актуальный список категорий

        // Основная компоновка
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Поле ввода штрих-кода с иконкой поиска
            OutlinedTextField(
                value = barcodeInput,
                onValueChange = { barcodeInput = it },
                label = { Text("Штрих-код") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.processIntent(QRcodeMenuIntents.OpenProductSearch)
                        }
                        ) {
                        Icon(
                            painter = painterResource(Res.drawable.search),
                            contentDescription = "Поиск"
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp) // Стандартная высота TextField
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Помогающий текст под полем ввода
            Text(
                text = "Введите штрих-код для генерации",
                style = MaterialTheme.typography.body2,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Центрируем изображение и описание
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Занимаем оставшееся пространство
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Изображение штрих-кода
                Image(
                    painter = painterResource(Res.drawable.barcode),
                    contentDescription = "Штрих-код",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Описание продукта
                Text(
                    text = productInfo,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопки выбора принтера и настроек
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Кнопка выбора принтера
                Button(
                    onClick = { printersExpanded = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(text = if (selectedPrinter.isEmpty()) "Выберите категорию" else selectedPrinter)
                }

                // Кнопка настроек с той же высотой, что и кнопка принтера
                Button(
                    onClick = { /* Обработка настроек */ },
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .height(48.dp) // Высота кнопки принтера
                        .width(48.dp),
                    contentPadding = PaddingValues(0.dp) // Убираем внутренние отступы
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.settings),
                        contentDescription = "Настройки"
                    )
                }
            }

            // Выпадающее меню выбора принтера
            DropdownMenu(
                expanded = printersExpanded,
                onDismissRequest = { printersExpanded = false }
            ) {
                printers.forEach { printer ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPrinter = printer
                            printersExpanded = false
                        }
                    ) {
                        Text(printer)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Кнопка печати
            Button(
                onClick = { /* Обработка печати */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Печать")
            }
        }
    }
}