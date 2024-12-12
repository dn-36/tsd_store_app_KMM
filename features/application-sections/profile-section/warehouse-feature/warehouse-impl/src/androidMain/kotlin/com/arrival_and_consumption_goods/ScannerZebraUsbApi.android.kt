package com.arrival_and_consumption_goods

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrival_and_consumption_goods.viewmodel.ScannerZebraUsbIntents
import com.arrival_and_consumption_goods.viewmodel.ScannerZebraUsbViewModel
import com.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.warehouse.viewmodel.WarehouseIntents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform.getKoin
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel


actual class ScannerZebraUsbScreen actual constructor( ) {

    val viewModel = ScannerZebraUsbViewModel(getKoin().get())

    @Composable

     actual fun Content ( listProducts: List<AllProductArrivalAndConsumption>,

                         onClickAdd: (sku: String ) -> Unit,

                         onClickNewProductAdd: (sku: String ) -> Unit,

                          onClickBack:() -> Unit
    ) {

         val scope = rememberCoroutineScope()

        scope.launch(Dispatchers.IO) {

            viewModel.scannersListHasBeenUpdated()

        }

        viewModel.customization()

        var counter by remember { mutableStateOf(0) }

        LaunchedEffect(Unit) {
            // Первоначальный вызов функции сразу
            //viewModel.scannersListHasBeenUpdated()

            // После этого, вызываем каждый 2 секунды
            while (true) {

                delay(3000)

                counter++ // Увеличиваем счетчик
            }
        }

        Box( modifier = Modifier.fillMaxSize().background(Color.White) ) {

            Column( modifier = Modifier.fillMaxSize().padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally ) {

                Row( modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Приход Расход", color = Color.Black, fontSize = 20.sp)

                }

                Text(text = "Счетчик: $counter", modifier = Modifier.alpha(0f)
                ) // Используем состояние counter для UI

               // Spacer( modifier = Modifier.height(10.dp) )

                Text (

                    text = "Подсоедините кабель сканера к телефону и разрешите подключиться к нему нажав кнопку 'да'",

                    modifier = Modifier.fillMaxWidth(0.9f), fontSize = 16.sp,

                    textAlign = TextAlign.Center
                )

                Spacer( modifier = Modifier.height(20.dp) )

                Text( text = "Результат сканирования:", fontSize = 16.sp )

                Spacer( modifier = Modifier.height(10.dp) )

                Text( text = viewModel.state.scanData, fontSize = 18.sp )

                Spacer( modifier = Modifier.height(10.dp) )

                Text( text = viewModel.state.textNewProduct, textAlign = TextAlign.Center )

                Spacer( modifier = Modifier.height(20.dp) )

                    //if ( viewModel.state.counter == 0 ) {

                    //CircularProgressIndicator()

                    //Spacer( modifier = Modifier.height(10.dp) )

                //}

                Spacer(modifier = Modifier.height(10.dp))

                Button(

                    onClick = {

                        if ( viewModel.state.scanData.isNotBlank() ) {

                            if ( viewModel.state.checkSku == null ||

                                viewModel.state.checkSku!! == true

                            ) {

                                viewModel.processIntents(

                                    ScannerZebraUsbIntents.CheckSku(

                                        viewModel.state.scanData, listProducts
                                    )
                                )

                                if ( viewModel.state.checkSku!! ) {

                                    onClickAdd(viewModel.state.scanData)

                                }
                            }
                            else if (!viewModel.state.checkSku!!) {

                                onClickNewProductAdd(viewModel.state.scanData)
                            }
                        }
                    },

                    modifier = Modifier

                        .clip(RoundedCornerShape(70.dp))

                        .height(40.dp)

                        .fillMaxWidth()

                ) {

                    Text(text = "Добавить")

                }
            }
        }
    }
}


