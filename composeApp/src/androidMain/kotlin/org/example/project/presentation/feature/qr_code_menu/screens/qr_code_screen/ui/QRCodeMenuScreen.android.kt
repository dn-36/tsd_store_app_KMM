package org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.presentation.core.models.ProductPresentationModel
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.QRcodeMenuIntent
import org.example.project.presentation.feature.qr_code_menu.screens.qr_code_screen.viewmodel.QRcodeMenuViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin

import tsdstorekmm.composeapp.generated.resources.Res
import tsdstorekmm.composeapp.generated.resources.barcode
import tsdstorekmm.composeapp.generated.resources.search
import tsdstorekmm.composeapp.generated.resources.settings

actual class QRCodeMenuScreen : Screen {

      actual var product = ProductPresentationModel()



    val viewModel =
        QRcodeMenuViewModel(getKoin().get(),getKoin().get(),getKoin().get(),getKoin().get())
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
     override fun Content() {

        //var productTsdStore by remember { mutableStateOf("product") }

        var selectedPrinter by remember { mutableStateOf("VKP принтер") }
        var printersExpanded by remember { mutableStateOf(false) }
        val printers = listOf(
            "TSC принтер",
            "VKP принтер",
            "Zebra принтер"
        )

        val state by viewModel.state.collectAsState()

        viewModel.processIntent(
            QRcodeMenuIntent.SetScreen(
                product,
                LocalNavigator.currentOrThrow
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column {
                    OutlinedTextField(
                        value = product.title,
                        onValueChange = {
                            /* viewModel.state = viewModel.state.copy(
                                titleProduct = it
                            )*/
                        },
                        label = { Text("Штрих-код") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    viewModel.processIntent(QRcodeMenuIntent.OpenProductSearch)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.search),
                                    contentDescription = "Поиск",
                                    modifier = Modifier.size(30.dp)
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
                }

                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Изображение штрих-кода
                    if (state.imgBitmap != null) {

                        Image(
                            bitmap = state.imgBitmap!!.asImageBitmap(),
                            modifier = Modifier
                                .width(300.dp)
                                .height(
                                    (product.heightQRcode * 5).dp
                                ),
                            contentDescription = "qrCode"
                        )
                        Spacer(modifier = Modifier.height(22.dp))
                        Image(
                            bitmap = state.titleProductQRcodeBiteMap!!.asImageBitmap(),
                            modifier = Modifier
                                .width(350.dp)
                                .wrapContentHeight(),
                            contentDescription = "qrCode"
                        )
                    } else {
                        Image(
                            painter = painterResource(Res.drawable.barcode),
                            "qrCode"
                        )
                        Text(
                            text = "product",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Описание продукта
                    /*    Text(
                        text = state.titleProduct ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.fillMaxWidth()
                    )*/


                }


                Spacer(modifier = Modifier.height(10.dp))


                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(15.dp)
                                .height(50.dp)
                                .fillMaxWidth(0.7F)
                        ) {
                            Card(
                                border = BorderStroke(1.dp, Color.Gray),
                                onClick = { printersExpanded = true },
                                modifier = Modifier.fillMaxSize(),
                            ) {}
                            Text(
                                text = selectedPrinter,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(48.dp)
                                .width(48.dp)
                                .clickable { viewModel.processIntent(QRcodeMenuIntent.OpenSettingsSizeQRCode) },
                            painter = painterResource(Res.drawable.settings),
                            contentDescription = "Настройки"
                        )
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
                                Text(
                                    modifier = Modifier,
                                    text = printer
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            Button(
                onClick = { viewModel.processIntent(
                    QRcodeMenuIntent.PrintQRcode(
                        ProductPresentationModel(
                            product.title,
                            product.qrCodeData,
                            state.heightQRcode,
                            state.fontSize
                        )

                    )
                ) },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)

            ) {
                Text(text = "Печать")
            }

            if (state.isOpenedSettings) {
                if (state.imgBitmap != null) {
                    QRcodeSizeComponent.Content(
                        state.imgBitmap!!,
                        state.titleProductQRcodeBiteMap!!,
                        state.heightQRcode,
                        state.fontSize,
                        {
                            viewModel.processIntent(
                                QRcodeMenuIntent.ChangeFontSize(
                                    it,
                                    product.title
                                )
                            )
                        },
                        {
                            viewModel.processIntent(
                                QRcodeMenuIntent.ChangeHeightQrCode(
                                    it,
                                    product.qrCodeData ?: ""
                                )
                            )
                        },
                        { viewModel.processIntent(QRcodeMenuIntent.CloseSettings) },
                        { viewModel.processIntent(QRcodeMenuIntent.SavedSettings) },
                    )
                } else {
                    Toast.makeText(LocalContext.current, "Выбирите продукт", Toast.LENGTH_SHORT).show()
                }
            }

            }
        }
    }


