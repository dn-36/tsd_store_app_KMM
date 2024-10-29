package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

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
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.DataEntryComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import com.project.core_app.network_base_screen.NetworkComponent
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection

class ArrivalAndConsumptionComponent ( override val viewModel: ArrivalAndConsumptionViewModel) :

    NetworkComponent {

    @Composable

    override fun Component () {

        val scope = rememberCoroutineScope()

        viewModel.processIntent(ArrivalAndConsumptionIntents.GetArrivalAndConsumptionGoods(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Приход Расход", color = Color.Black, fontSize = 20.sp)

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn (modifier = Modifier.fillMaxHeight(0.8f)) {
                    items(viewModel.state.listAllArrivalOrConsumption) { item ->

                        Item(item, onDelete = { ui ->

                            viewModel.processIntent(
                                ArrivalAndConsumptionIntents.OpenDeleteComponent(

                                    item

                                )
                            )
                        },

                            onUpdate = { item ->
                                viewModel.processIntent(
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
                            onClick = { viewModel.processIntent(ArrivalAndConsumptionIntents.ArrivalOrConsumption( scope, 1 )) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.45f)
                        ) {
                            Text(text = "Приход")
                        }
                        Button(
                            onClick = { viewModel.processIntent(ArrivalAndConsumptionIntents.ArrivalOrConsumption( scope, 0 )) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.9f)
                        ) {
                            Text(text = "Расход")
                        }
                    }

                    if( viewModel.state.isVisibilityAddProductsComponent.value != 1f ) {

                        MenuBottomBarWarehouse().Content(MenuBottomBarWarehouseSection.FINANCE)

                    }

                }
            }
        }
        if (viewModel.state.isVisibilityDataEntryComponent.value == 1f) {

            DataEntryComponent(

                viewModel.state.updatedContragentExpense,

                viewModel.state.updatedContragentParish,

                viewModel.state.updatedEntityExpense,

                viewModel.state.updatedEntityParish,

                viewModel.state.updatedWarehouse,

                listAllContragents = viewModel.state.listAllContragent,

                listAllWarehouse = viewModel.state.listAllWarehouse,

                onClickBack = { viewModel.processIntent(ArrivalAndConsumptionIntents.BackDataEntry) },

                onClickNext = { idLegalEntityParish: Int?, idLegalEntityExpense: Int?, idContragentExpense: Int?, idContragentParish: Int?, idWarehouse: Int? ->

                    viewModel.processIntent(
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

        } else if (viewModel.state.isVisibilityListProducts.value == 1f) {

            println("AAAA: ${viewModel.state.listProducts}")

            ListProductsComponent(viewModel.state.listProducts, onClickBack = {

                viewModel.processIntent(ArrivalAndConsumptionIntents.BackListProducts)

            }, onClickProduct = { selectedProducts ->

                viewModel.processIntent(ArrivalAndConsumptionIntents.SelectProducts(selectedProducts))

            }).Content()

        } else if (viewModel.state.isVisibilityCountProducts.value == 1f) {

            CountProductComponent(

                viewModel.state.listProducts,

                onClickReady = { count ->

                    viewModel.processIntent(ArrivalAndConsumptionIntents.Ready(count))

                },

                colorBorderCountTF = viewModel.state.colorBorderCountTF

            ).Content()

        } else if (viewModel.state.isVisibilityAddProductsComponent.value == 1f) {

            AddProductsComponent(

                isUpdate = viewModel.state.isUpdate,

                viewModel.state.listSelectedProducts,

                onClickSelectFromList = { scope ->

                    viewModel.processIntent(ArrivalAndConsumptionIntents.SelectFromList)

                },

                onClickBack = { viewModel.processIntent(ArrivalAndConsumptionIntents.BackAddProducts) },

                onClickCreate = { scope ->
                    viewModel.processIntent(
                        ArrivalAndConsumptionIntents.CreateArrivalOrConsumption(
                            scope
                        )
                    )
                },

                onClickUpdate = { scope ->

                    viewModel.processIntent( ArrivalAndConsumptionIntents.Update( scope ) )

                },

                onClickScanner = { viewModel.processIntent(ArrivalAndConsumptionIntents.Scanner) },

                onClickCansel = { index ->

                    viewModel.processIntent(ArrivalAndConsumptionIntents.CanselSelectedProduct( index )) }

            ).Content()

        }

        else if ( viewModel.state.isVisibilityScannerComponent.value == 1f ) {

            ScannerComponent( onClickAdd = { name ->

                viewModel.processIntent(ArrivalAndConsumptionIntents.AddProductScanner( name ))

            }, onClickCansel = { viewModel.processIntent(ArrivalAndConsumptionIntents.CanselScanner) } )

                .QrScannerView()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent.value == 1f ) {

            DeleteComponent( onClickNo = {

                viewModel.processIntent(ArrivalAndConsumptionIntents.NoDelete) },

                onClickDelete = { scope -> viewModel.processIntent(

                    ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption( scope )) }).Content()

        }

    }

}