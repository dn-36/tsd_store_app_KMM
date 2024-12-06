package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arrival_and_consumption_goods.ScannerZebraUsbScreen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.ui.DataEntryComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.ListProductsComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.scanner_camera_component.ScannerCameraComponent
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.util.formatDateTime
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import org.example.project.core.menu_bottom_bar.ui.MenuBottomBarWarehouse
import org.example.project.presentation.profile_feature.core.menu_bottom_bar_profile.viewmodel.MenuBottomBarWarehouseSection
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.update_pencil

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

                SearchComponent( onValueChange = { text -> viewModel.processIntent(

                    ArrivalAndConsumptionIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn (modifier = Modifier.fillMaxHeight(0.75f)) {

                    itemsIndexed(viewModel.state.listFilteredArrivalOrConsumption)

                    { index,item ->

                        Box(modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {
                                    if (  viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntent(

                                            ArrivalAndConsumptionIntents.OnePressItem)
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntent(

                                        ArrivalAndConsumptionIntents.LongPressItem(index))

                                },
                            )

                        }) {

                            Row(modifier = Modifier.fillMaxWidth()) {

                                Column {

                                    if (item.is_push == 1) {

                                        Text("Приход", fontSize = 17.sp, fontWeight = FontWeight.Bold)

                                    }
                                    else{

                                        Text("Расход", fontSize = 17.sp, fontWeight = FontWeight.Bold)

                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Описание: ${item.text}", fontSize = 15.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Контрагент: ${item.contragent!!.name}", fontSize = 15.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Склад: ${item.store!!.name}", fontSize = 15.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                }

                            }

                            Text( formatDateTime(item.created_at!!), fontSize = 13.sp,

                                modifier = Modifier.align(Alignment.BottomEnd)
                                    .padding(bottom = 8.dp))

                            if (  viewModel.state.listAlphaTools.size > index &&

                                viewModel.state.listAlphaTools[index] == 1f ) {

                                Row(modifier = Modifier.align(Alignment.TopEnd)) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,

                                        modifier = Modifier.size(20.dp).clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            viewModel.processIntent(
                                                ArrivalAndConsumptionIntents.OpenUpdateDataEntry(
                                                    scope,
                                                    item
                                                )
                                            )
                                        }
                                    )

                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(painter = painterResource(Res.drawable.cancel),
                                        contentDescription = null,

                                        modifier = Modifier.size(20.dp).clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            viewModel.processIntent(
                                                ArrivalAndConsumptionIntents.OpenDeleteComponent(

                                                    item
                                                )
                                            )
                                        }
                                    )

                                }

                            }

                        }

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
                            onClick = { viewModel.processIntent(

                                ArrivalAndConsumptionIntents.ArrivalOrConsumption(

                                    scope, 1 )) },

                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.45f)
                        ) {
                            Text(text = "Приход")
                        }
                        Button(
                            onClick = { viewModel.processIntent(

                                ArrivalAndConsumptionIntents.ArrivalOrConsumption(

                                    scope, 0 )) },

                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.9f)
                        ) {
                            Text(text = "Расход")
                        }
                    }

                    if( viewModel.state.isVisibilityAddProductsComponent != 1f ) {

                        MenuBottomBarWarehouse().Content(MenuBottomBarWarehouseSection.FINANCE)

                    }

                }
            }
        }
        if ( viewModel.state.isVisibilityDataEntryComponent == 1f) {

            DataEntryComponent(

                viewModel.state.description,

                viewModel.state.isPush,

                viewModel.state.updatedContragentExpense,

                viewModel.state.updatedContragentParish,

                viewModel.state.updatedEntityExpense,

                viewModel.state.updatedEntityParish,

                viewModel.state.updatedWarehouse,

                listAllContragents = viewModel.state.listAllContragent,

                listAllWarehouse = viewModel.state.listAllWarehouse,

                onClickBack = { viewModel.processIntent(ArrivalAndConsumptionIntents.BackFromDataEntry) },

                onClickNext = { description, idLegalEntityParish, idLegalEntityExpense,

                                idContragentExpense, idContragentParish, idWarehouse ->

                    viewModel.processIntent(

                        ArrivalAndConsumptionIntents.Next(

                            description, idLegalEntityParish, idLegalEntityExpense,

                            idContragentExpense, idContragentParish, idWarehouse
                        )
                    )
                }

            ).Content()

        } else if (viewModel.state.isVisibilityListProducts == 1f) {

            println("AAAA: ${viewModel.state.listProducts}")

            ListProductsComponent(viewModel.state.listProducts, onClickBack = {

                viewModel.processIntent(ArrivalAndConsumptionIntents.BackFromListProducts)

            }, onClickProduct = { selectedProducts ->

                viewModel.processIntent(ArrivalAndConsumptionIntents.SelectProducts(selectedProducts))

            }).Content()

        } else if (viewModel.state.isVisibilityCountProducts == 1f) {

            CountProductComponent(

                viewModel.state.listProducts,

                onClickReady = { count ->

                    viewModel.processIntent(ArrivalAndConsumptionIntents.Ready(count))

                },

                colorBorderCountTF = viewModel.state.colorBorderCountTF

            ).Content()

        } else if ( viewModel.state.isVisibilityAddProductsComponent == 1f) {

            AddProductsComponent(

                isUpdate = viewModel.state.isUpdate,

                viewModel.state.listSelectedProducts,

                onClickSelectFromList = {

                    viewModel.processIntent(ArrivalAndConsumptionIntents.SelectFromList)

                },

                onClickBack = { viewModel.processIntent(

                    ArrivalAndConsumptionIntents.BackFromAddProducts) },

                onClickCreate = { viewModel.processIntent(

                        ArrivalAndConsumptionIntents.CreateArrivalOrConsumption( scope) ) },

                onClickUpdate = {

                    viewModel.processIntent( ArrivalAndConsumptionIntents.Update( scope ) )

                },

                onClickScannerCamera = { viewModel.processIntent(

                    ArrivalAndConsumptionIntents.ScannerCamera) },

                onClickScannerZebraUsb = { viewModel.processIntent(

                    ArrivalAndConsumptionIntents.ScannerZebraUsb) },

                onClickCansel = { index ->

                    viewModel.processIntent(

                        ArrivalAndConsumptionIntents.CanselSelectedProduct( index )) } ).Content()

        }

        else if ( viewModel.state.isVisibilityScannerCameraComponent == 1f ) {

            ScannerCameraComponent( onClickAdd = { name ->

                viewModel.processIntent(ArrivalAndConsumptionIntents.AddProductScanner( name ))

            }, onClickCansel = { viewModel.processIntent(

                ArrivalAndConsumptionIntents.CanselScanner) } ) .ScannerView()

        }

        else if ( viewModel.state.isVisibilityScannerZebraUsbComponent  ) {

            ScannerZebraUsbScreen().Content()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent == 1f ) {

            DeleteComponent(

                name = "",

                onClickNo = {

                viewModel.processIntent(ArrivalAndConsumptionIntents.NoDelete) },

                onClickDelete = { viewModel.processIntent(

                    ArrivalAndConsumptionIntents.DeleteArrivalOrConsumption( scope )) }).Content()

        }

    }

}