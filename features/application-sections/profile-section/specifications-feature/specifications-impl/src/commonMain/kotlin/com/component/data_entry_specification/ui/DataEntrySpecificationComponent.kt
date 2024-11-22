package com.component.data_entry_specification.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.component.data_entry_specification.viewmodel.DataEntrySpecificationIntents
import com.component.data_entry_specification.viewmodel.DataEntrySpecificationViewModel
import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.StoreModel
import com.model.WarehouseModel
import com.model.SpecificResponseModel
import com.project.core_app.utils.boxHeight
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntrySpecificationComponent(

    val listWarehouse: List<WarehouseModel>,

    val listProducts: List<ProductResponseModel>,

    val listCurrency: List<CurrencyResponseModel>,

    val item: SpecificResponseModel?,

    val selectedCurrency: CurrencyResponseModel?,

    val selectedWarehouse: WarehouseModel?,

    val selectedStatus: Pair<String,Int>?,

    val selectedName: String,

    val onClickBack: () -> Unit,

    val onClickNext: (

        name: String,

        selectedCurrency: CurrencyResponseModel?,

        selectedWarehouse: WarehouseModel?,

        selectedStatus: Pair<String,Int>?

    ) -> Unit, ) {

    val viewModel = DataEntrySpecificationViewModel()

    @Composable

    fun Content () {

        viewModel.processIntents(DataEntrySpecificationIntents.SetScreen( item = item, listProducts = listProducts,

            listCurrency = listCurrency, listWarehouse = listWarehouse,

            selectedCurrency = selectedCurrency, selectedStatus = selectedStatus,

            selectedWarehouse = selectedWarehouse, selectedName = selectedName))

        Box(modifier = Modifier.fillMaxSize().background(Color.White).clickable(
            indication = null, // Отключение эффекта затемнения
            interactionSource = remember { MutableInteractionSource() })

        { })

        {
            Column(modifier = Modifier.padding(16.dp),) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Спецификации", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                    viewModel.processIntents(DataEntrySpecificationIntents.InputTextName(it))

                    },

                    label = { Text("Название", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.currency,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntrySpecificationIntents.InputTextCurrency(inputText,

                                listCurrency.filter {

                                    it.name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Валюта") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntrySpecificationIntents.MenuCurrency)

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.down_arrow),
                                contentDescription = "Поиск",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                if (viewModel.state.selectedCurrency != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedCurrency!!.name,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp)
                                .align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    viewModel.processIntents(

                                        DataEntrySpecificationIntents.DeleteSelectedCurrency

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedCurrency) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.filteredListCurrency.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.filteredListCurrency)

                            { index, item ->

                                if (item.name != "") {

                                    Text(item.name,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntrySpecificationIntents.SelectedCurrency(

                                                        item
                                                    )
                                                )

                                            })

                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.warehouse,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntrySpecificationIntents.InputTextWarehouse(inputText,

                                listWarehouse.filter {

                                    it.name!!.contains(inputText, ignoreCase = true)

                                }))

                    },

                    label = { Text("Склад") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntrySpecificationIntents.MenuWarehouse)

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.down_arrow),
                                contentDescription = "Поиск",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                if (viewModel.state.selectedWarehouse != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedWarehouse!!.stores[0]!!.name?:"Нет имени",
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp)
                                .align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    viewModel.processIntents(

                                        DataEntrySpecificationIntents.DeleteSelectedWarehouse

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedWarehouse) {

                    Box(modifier = Modifier.fillMaxWidth().height(

                        boxHeight( viewModel.state.filteredListWarehouse.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(if (viewModel.state.filteredListWarehouse.size != 0) viewModel.state.filteredListWarehouse

                            else listOf ( WarehouseModel( stores = listOf( StoreModel( id = 0,

                                name = "Склад не найден" , ui = null ) ), name = " " ) ))

                            { index, item ->

                                    Text( item.stores[0]!!.name?:"Нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntrySpecificationIntents.SelectedWarehouse(

                                                        item
                                                    )
                                                )

                                            })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = "",

                    onValueChange = {

                    },

                    label = { Text("Статус") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntrySpecificationIntents.MenuStatus)

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.down_arrow),
                                contentDescription = "Поиск",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                if (viewModel.state.selectedStatus != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedStatus!!.first,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )

                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp)
                                .align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    viewModel.processIntents(

                                        DataEntrySpecificationIntents.DeleteSelectedStatus

                                    )

                                })
                    }
                }

                if (viewModel.state.expendedStatus) {

                    //0 отмена, 1 новый, 2 подтвержден, 3 оплачен, 5 отгружен

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( listOf("Отмена","Новый","Подтвержден","Оплачен") )

                            { index, item ->

                                    Text( item ,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntrySpecificationIntents.SelectedStatus(

                                                        item, index
                                                    )
                                                )

                                            })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))


                    Button(

                        onClick = {

                                  onClickNext(

                                      viewModel.state.name,

                                      viewModel.state.selectedCurrency,

                                      viewModel.state.selectedWarehouse,

                                      viewModel.state.selectedStatus )
                        },

                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()

                    ) {

                        Text(text = "Далее")

                    }

            }
        }
    }
}