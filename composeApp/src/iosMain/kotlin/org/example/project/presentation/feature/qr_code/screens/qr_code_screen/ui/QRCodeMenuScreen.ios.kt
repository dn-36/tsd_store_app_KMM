package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.presentation.core.models.ProductPresentationModel

/*
actual class QRCodeMenuScreen : Screen {

    val productTitle: String = "product TSDstore"


    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val viewModel = QRcodeMenuViewModel(getKoin().get(),getKoin().get())

        var selectedPrinter by remember { mutableStateOf("VKP принтер") }
        var printersExpanded by remember { mutableStateOf(false) }
        val printers = listOf(
            "TSC принтер",
            "VKP принтер",
            "Zebra принтер"
        )

        viewModel.processIntent(
            QRcodeMenuIntent.SetScreen(
                titleProduct = productTitle,
                LocalNavigator.currentOrThrow
            ))

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column {
                    OutlinedTextField(
                        value = QRcodeMenuViewModel.state.titleProduct,
                        onValueChange = {
                            QRcodeMenuViewModel.state = QRcodeMenuViewModel.state.copy(
                                titleProduct = it
                            )
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
                    ImageBitmap( 200,200)

                    Spacer(modifier = Modifier.height(8.dp))

                    // Описание продукта
                    Text(
                        text = QRcodeMenuViewModel.state.titleProduct ,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))


                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.padding(15.dp).height(50.dp)
                                .fillMaxWidth(0.7F)
                        ) {
                            Card(
                                border = BorderStroke(1.dp, Color.Gray),
                                onClick = { printersExpanded = true },
                                modifier = Modifier.fillMaxSize(),
                            ) {}
                            Text(
                                text =  selectedPrinter,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(48.dp)
                                .width(48.dp),

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
                onClick = { viewModel.processIntent(QRcodeMenuIntent.PrintQRcode) },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)

            ) {
                Text(text = "Печать")
            }
        }
    }

    actual var product: String
        get() = TODO("Not yet implemented")
        set(value) {}
}

*/
actual class QRCodeMenuScreen : Screen {
    //}
    actual var product: ProductPresentationModel
        get() = ProductPresentationModel()
        set(value) {}
     @Composable
    override fun Content() {

    }
}