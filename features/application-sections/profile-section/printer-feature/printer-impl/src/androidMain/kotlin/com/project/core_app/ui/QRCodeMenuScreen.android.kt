package org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.profile.profile.qr_code.core.ProductPresentationModel
import com.project.core_app.ui.components.BarCodeVkpComponent
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.ListBluetoothDevicesComponent
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.QRcodeSizeComponent
import org.example.project.presentation.feature.qr_code.screens.qr_code_screen.ui.components.SettingsTicketsTSCprinterComponent
import com.project.core_app.viewmodel.model.CategoryPrinter
import com.project.core_app.viewmodel.QRcodeMenuIntent
import com.project.core_app.viewmodel.QRcodeMenuViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.bluetooth
import project.core.resources.search
import project.core.resources.settings

actual class QRCodeMenuScreen : Screen {
   actual var product = ProductPresentationModel()

    val viewModel =
        QRcodeMenuViewModel(
            getKoin().get(),
            getKoin().get(),
            getKoin().get(),
            getKoin().get(),
            getKoin().get(),
            getKoin().get(),
            getKoin().get(),
            getKoin().get()
        )
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
     override fun Content() {

        val scope = rememberCoroutineScope()
        var printersExpanded by remember { mutableStateOf(false) }
        val printers = listOf(
            CategoryPrinter.VKP,
            CategoryPrinter.TSC,
            CategoryPrinter.ZEBRA
        )

        val state by viewModel.state.collectAsState()

        viewModel.processIntent(
            QRcodeMenuIntent.SetScreen(
                product,
                LocalNavigator.currentOrThrow,
                LocalContext.current

            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(if (state.isLoadingDataOnScreen) 0F else 1F)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column {
                    OutlinedTextField(
                        value = product.title,
                        onValueChange = {

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

                when(state.categoryPrinter) {

                    CategoryPrinter.VKP -> BarCodeVkpComponent(
                        state.imgBitmap,
                        state.titleProductQRcodeBiteMap,
                        state.heightQRcode
                    )

                    CategoryPrinter.TSC -> {
                        SettingsTicketsTSCprinterComponent(
                            state.imgBitmap!!,
                            state.titleProductQRcodeBiteMap!!,
                            { x: Int, y: Int, height: Int, weight: Int ->
                                viewModel.processIntent(
                                    QRcodeMenuIntent.ChangeSettingsTsc(
                                        x, y, height, weight
                                    )
                                )
                            })
                        Spacer(modifier = Modifier.height(20.dp))
                    }



                    CategoryPrinter.ZEBRA ->{ }

                }






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
                                text = state.categoryPrinter.toString,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        Icon(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .height(30.dp)
                                .width(30.dp)
                                .clickable { viewModel.processIntent(QRcodeMenuIntent.OpenSettingsPrinter(scope)) },
                            painter = painterResource(Res.drawable.settings),
                            contentDescription = "Настройки"
                        )
                        if(state.categoryPrinter == CategoryPrinter.TSC) {
                            Image(
                                painter = painterResource(Res.drawable.bluetooth),
                                modifier = Modifier
                                    .padding(start = 15.dp)
                                    .size(30.dp)
                                    .clickable {
                                        viewModel.processIntent(QRcodeMenuIntent.OpenSettingsBluetooth(scope))
                                    }
                                ,
                                contentDescription = null
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = printersExpanded,
                        onDismissRequest = { printersExpanded = false }
                    ) {
                        printers.forEach { printer ->
                            DropdownMenuItem (
                                onClick = {
                                    viewModel.processIntent(QRcodeMenuIntent.СhoicePrinter(printer))
                                    printersExpanded = false
                                }
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = printer.toString
                                )
                            }
                        }
                    }
                }



                    Box(Modifier.fillMaxWidth()) {
Row {


                        Text(
                            state.statusConnected.text,
                            Modifier.padding(start = 15.dp).align(Alignment.Top),
                            fontSize = 12.sp,
                            color = state.statusConnected.colorText
                        )
                        if(state.isLoadingConnectionDevice){
                        Row(Modifier.align(Alignment.Top))  {
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                "Подключение к устройству",
                                 Modifier.align(Alignment.CenterVertically),
                                fontSize = 10.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            CircularProgressIndicator(
                                Modifier.size(25.dp).align(Alignment.CenterVertically),
                            )

                        }
                    }
    }
                }





            }

            Button(
                onClick = { viewModel.processIntent(
                    QRcodeMenuIntent.PrintQRcode(
                        ProductPresentationModel(
                            product.title,
                            product.qrCodeData,
                            state.heightQRcode,
                            state.fontSize
                        ),
                       getKoin().get()
                    )
                ) },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(bottom = 16.dp)
                    .align(Alignment.BottomCenter)

            ) {
                Text(text = "Печать")
            }

            if (state.isOpenedSettingsVKP) {
                if (state.imgBitmap != null) {
                    QRcodeSizeComponent.Content(
                        state.imgBitmap!!,
                        state.titleProductQRcodeBiteMap!!,
                        state.heightQRcode,
                        state.fontSize,
                        state.categoryPrinter,
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
                        { viewModel.processIntent(QRcodeMenuIntent.CloseSettingsVKP) },
                        { viewModel.processIntent(QRcodeMenuIntent.SavedSettings) },
                    )
                } else {
                    Toast.makeText(LocalContext.current, "Выбирите продукт", Toast.LENGTH_SHORT).show()
                }
            }
            if(state.isOpenedBluetoothSettingsTSC){
                ListBluetoothDevicesComponent(
                    state.bluetoothDeviceList,
                    {viewModel.processIntent(QRcodeMenuIntent.SelectBluetoothDevice(it,scope))},
                    {viewModel.processIntent(QRcodeMenuIntent.CloseSettingsBluetooth(scope))},
                    {viewModel.processIntent(QRcodeMenuIntent.SearchBluetoothDevice(scope) )},
                    state.statusSearchBluetoothDevice
                )
            }

            }
        }
    }



