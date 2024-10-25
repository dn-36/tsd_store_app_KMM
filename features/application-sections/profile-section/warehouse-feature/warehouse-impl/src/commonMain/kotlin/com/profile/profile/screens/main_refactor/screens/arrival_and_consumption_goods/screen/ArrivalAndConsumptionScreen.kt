package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.AddProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.CountProductComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.DataEntryComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.Item
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.ScannerComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.koin.mp.KoinPlatform.getKoin

class ArrivalAndConsumptionScreen : Screen {

    val vm: ArrivalAndConsumptionViewModel = getKoin().get()

    @Composable

    override fun Content() {

        val scope = rememberCoroutineScope()

        vm.processIntent(ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Приход Расход", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn (modifier = Modifier.fillMaxHeight(0.8f)) {
                    items(vm.state.listAllArrivalOrConsumption) { item ->

                        Item(item, onDelete = { ui ->

                            vm.processIntent(
                                ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption(
                                    scope,
                                    ui
                                )
                            )
                        },

                            onUpdate = { item ->
                                vm.processIntent(
                                    ArrivalAndConsumptionIntents.UpdateButton(
                                        scope,
                                        item
                                    )
                                )
                            })

                        Spacer(modifier = Modifier.height(10.dp))

                    }
                }

            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {

                Column {

                    Row(
                        modifier = Modifier.padding(10.dp).fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = { vm.processIntent(ArrivalAndConsumptionIntents.ArrivalOrConsumption( scope, 1 )) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.45f)
                        ) {
                            Text(text = "Приход")
                        }
                        Button(
                            onClick = { vm.processIntent(ArrivalAndConsumptionIntents.ArrivalOrConsumption( scope, 0 )) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.9f)
                        ) {
                            Text(text = "Расход")
                        }
                    }

                    if( vm.state.isVisibilityAddProductsComponent.value != 1f ) {

                        MenuBottomBarWarehouse().Content()

                    }

                }
            }
        }
        if (vm.state.isVisibilityDataEntryComponent.value == 1f) {

            DataEntryComponent(

                vm.state.updatedContragentExpense,

                vm.state.updatedContragentParish,

                vm.state.updatedEntityExpense,

                vm.state.updatedEntityParish,

                vm.state.updatedWarehouse,

                listAllContragents = vm.state.listAllContragent,

                listAllWarehouse = vm.state.listAllWarehouse,

                onClickBack = { vm.processIntent(ArrivalAndConsumptionIntents.BackDataEntry) },

                onClickNext = { idLegalEntityParish: Int?, idLegalEntityExpense: Int?, idContragentExpense: Int?, idContragentParish: Int?, idWarehouse: Int? ->

                    vm.processIntent(
                        ArrivalAndConsumptionIntents.Next(
                            idLegalEntityParish,
                            idLegalEntityExpense,
                            idContragentExpense,
                            idContragentParish,
                            idWarehouse
                        )
                    )
                }

            ).Content()

        } else if (vm.state.isVisibilityListProducts.value == 1f) {

            println("AAAA: ${vm.state.listProducts}")

            ListProductsComponent(vm.state.listProducts, onClickBack = {

                vm.processIntent(ArrivalAndConsumptionIntents.BackListProducts)

            }, onClickProduct = { selectedProducts ->

                vm.processIntent(ArrivalAndConsumptionIntents.SelectProducts(selectedProducts))

            }).Content()

        } else if (vm.state.isVisibilityCountProducts.value == 1f) {

            CountProductComponent(

                vm.state.listProducts,

                onClickReady = { count ->

                    vm.processIntent(ArrivalAndConsumptionIntents.Ready(count))

                },

                colorBorderCountTF = vm.state.colorBorderCountTF

                ).Content()

        } else if (vm.state.isVisibilityAddProductsComponent.value == 1f) {

            AddProductsComponent(

                isUpdate = vm.state.isUpdate,

                vm.state.listSelectedProducts,

                onClickSelectFromList = { scope ->

                    vm.processIntent(ArrivalAndConsumptionIntents.SelectFromList)

                },

                onClickBack = { vm.processIntent(ArrivalAndConsumptionIntents.BackAddProducts) },

                onClickCreate = { scope ->
                    vm.processIntent(
                        ArrivalAndConsumptionIntents.CreateArrivalOrConsumption(
                            scope
                        )
                    )
                },

                onClickUpdate = { scope ->

                    vm.processIntent( ArrivalAndConsumptionIntents.Update( scope ) )

                },

                onClickScanner = { vm.processIntent(ArrivalAndConsumptionIntents.Scanner) },

                onClickCansel = { index ->

                    vm.processIntent(ArrivalAndConsumptionIntents.CanselSelectedProduct( index )) }

                ).Content()

        }

        else if ( vm.state.isVisibilityScannerComponent.value == 1f ) {

            ScannerComponent( onClickAdd = { name ->

                vm.processIntent(ArrivalAndConsumptionIntents.AddProductScanner( name ))

            }, onClickCansel = { vm.processIntent(ArrivalAndConsumptionIntents.CanselScanner) } )

                .QrScannerView()

        }

    }
}