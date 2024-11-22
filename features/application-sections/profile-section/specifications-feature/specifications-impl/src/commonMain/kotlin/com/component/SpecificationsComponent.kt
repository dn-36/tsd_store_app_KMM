package com.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.component.add_products.ui.AddProductsComponent
import com.component.data_entry_specification.ui.DataEntrySpecificationComponent
import com.component.list_products.ui.ListProductsComponent
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.util.formatDateTime
import com.viewmodel.SpecificationsIntents
import com.viewmodel.SpecificationsViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil

class SpecificationsComponent ( override val viewModel: SpecificationsViewModel) : NetworkComponent {

    @Composable

    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(SpecificationsIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White))

        {
            Column(modifier = Modifier.padding(16.dp),) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        {
                            if ( !viewModel.state.isVisibilityDeleteComponent ) {

                            viewModel.processIntents(SpecificationsIntents.Back)} }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Спецификации", color = Color.Black, fontSize = 20.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    SpecificationsIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn {

                    itemsIndexed(

                        viewModel.state.listFilteredSpecifications) { index,item ->

                        Box (modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {
                                    if (  viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntents(SpecificationsIntents.OnePressItem)
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntents(

                                        SpecificationsIntents.LongPressItem(index))

                                },
                            )

                        }) {
                            Column(modifier = Modifier.fillMaxWidth()) {

                                Spacer(modifier = Modifier.height(10.dp))

                                //0 отмена, 1 новый, 2 подтвержден, 3 оплачен, 5 отгружен

                                when (item.status) {

                                    0 -> Text(
                                        "Статус : Отмена", fontSize = 16.sp,

                                        color = Color(0xFF8B0000),

                                        fontWeight = FontWeight.Bold
                                    )

                                    1 -> Text("Статус : Новый", fontSize = 16.sp)

                                    2 -> Text("Статус : Подтвержден", fontSize = 16.sp)

                                    3 -> Text("Статус : Оплачен", fontSize = 16.sp)

                                    5 -> Text("Статус : Отгружен", fontSize = 16.sp)

                                    else -> Text("Статус : Не указан", fontSize = 16.sp)

                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Юр.лицо : ${
                                        if (item.entity_id != null)

                                            viewModel.state.listContragents.firstNotNullOfOrNull { contragent ->
                                                contragent.entities?.find { it.id == item.entity_id }
                                            }?.name ?: "Не указано"
                                        else "Не указано"
                                    }", fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Дата : ${formatDateTime(item.created_at ?: "")}",

                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Описание : ${item.text ?: "не указано"}", fontSize = 16.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(

                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.LightGray)
                                )

                            }

                            if (  viewModel.state.listAlphaTools.size > index &&

                                viewModel.state.listAlphaTools[index] == 1f ) {

                                Row(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                if (!viewModel.state.isVisibilityDeleteComponent) {

                                                    viewModel.processIntents(

                                                        SpecificationsIntents.OpenUpdateDataEntry(
                                                            scope,

                                                            item
                                                        )
                                                    )

                                                }
                                            })


                                    Spacer(modifier = Modifier.width(20.dp))

                                    Image(painter = painterResource(Res.drawable.cancel),
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                viewModel.processIntents(

                                                    SpecificationsIntents.OpenDeleteComponent(item)
                                                )
                                            })

                                }
                            }

                        }

                    }
                }

            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    if ( !viewModel.state.isVisibilityDeleteComponent ) {

                        viewModel.processIntents(SpecificationsIntents.OpenCreateDataEntry(scope))

                    }

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntry ) {

            DataEntrySpecificationComponent( listWarehouse = viewModel.state.listWarehouse,

                listProducts = viewModel.state.listProducts,

                listCurrency = viewModel.state.listCurrency,

                item = viewModel.state.updateItem,

                selectedWarehouse = if ( viewModel.state.updateItem != null )

                    viewModel.state.listWarehouse.find {

                        it.stores[0]!!.id?:0 == viewModel.state.updateItem?.local_store_id?:0 }

                else viewModel.state.selectedWarehouse,

                selectedCurrency = if ( viewModel.state.updateItem != null )

                    viewModel.state.listCurrency.find {

                        it.id == viewModel.state.updateItem?.valuta_id

                    } else viewModel.state.selectedCurrency,

                selectedStatus = if ( viewModel.state.updateItem != null)

                    when (viewModel.state.updateItem?.status?:0) {

                    ////0 отмена, 1 новый, 2 подтвержден, 3 оплачен, 5 отгружен

                    0 -> Pair("Отмена", 0)

                    1 -> Pair("Новый", 1)

                    2 -> Pair("Подтвержден", 2)

                    3 -> Pair("Оплачен", 3)

                    else -> Pair("Новый", 1)

                } else viewModel.state.selectedStatus,

                selectedName  = if ( viewModel.state.updateItem != null)

                    viewModel.state.updateItem?.text?:"" else viewModel.state.name,

                onClickBack = {

                    viewModel.processIntents(SpecificationsIntents.BackFromDataEntry) },

                onClickNext = { name,selectedCurrency, selectedWarehouse, selectedStatus ->

                    viewModel.processIntents(SpecificationsIntents.Next(name,selectedCurrency,

                        selectedWarehouse, selectedStatus))

                }).Content()

        }

        else if ( viewModel.state.isVisibilityAddProducts ) {

            AddProductsComponent(

                listElementSpecifications = viewModel.state.listElementsSpecifications,

                onClickChooseProduct = { list, index, byCategory ->

                viewModel.processIntents(SpecificationsIntents.OpenListProducts(list, index,

                    byCategory ))

            }, onClickBack = { viewModel.processIntents(

                SpecificationsIntents.BackFromAddProducts) },

                indexMainGroup = viewModel.state.indexMainGroup,

                byCategory = viewModel.state.byCategory,

                onClickSave = { list ->

                    if ( viewModel.state.updateItem == null ) {

                viewModel.processIntents(

                    SpecificationsIntents.CreateSpecification(scope,list))}

                else {

                        viewModel.processIntents(

                            SpecificationsIntents.UpdateSpecification(scope,list))

                } }).Content()

        }

        else if ( viewModel.state.isVisibilityListProducts ) {

            ListProductsComponent( listAllProducts = viewModel.state.listProducts,

                onClickBack = { viewModel.processIntents(

                    SpecificationsIntents.BackFromListProducts) },

                onClickProduct = { item ->  viewModel.processIntents(

                    SpecificationsIntents.SelectProduct(item))}).Content()

        }

        else if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent(

                name = "спецификацию",

                onClickNo = {

                viewModel.processIntents(SpecificationsIntents.NoDelete) },

                onClickDelete = { viewModel.processIntents(

                    SpecificationsIntents.DeleteSpecification(scope))}).Content()

        }

    }

}