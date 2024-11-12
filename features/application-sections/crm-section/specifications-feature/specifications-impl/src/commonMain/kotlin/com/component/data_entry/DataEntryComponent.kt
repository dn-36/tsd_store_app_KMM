package com.component.data_entry

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
import com.component.data_entry.viewmodel.DataEntryIntents
import com.component.data_entry.viewmodel.DataEntryViewModel
import com.model.CurrencyResponseModel
import com.model.ProductResponseModel
import com.model.StoreModel
import com.model.WarehouseModel
import com.component.data_entry.util.boxHeight
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryComponent (

    val listWarehouse: List<WarehouseModel>,

    val listProducts: List<ProductResponseModel>,

    val listCurrency: List<CurrencyResponseModel>,

    val onClickBack: () -> Unit

) {

    val viewModel = DataEntryViewModel()

    @Composable

    fun Content () {

        viewModel.processIntents(DataEntryIntents.SetScreen( listProducts = listProducts,

            listCurrency = listCurrency, listWarehouse = listWarehouse))

        Box(modifier = Modifier.fillMaxSize().background(Color.White))

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

                    Text("Создание", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.currency,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextCurrency(inputText,

                                listCurrency.filter {

                                    it.name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Валюта") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuCurrency)

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

                                        DataEntryIntents.DeleteSelectedCurrency

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

                                                    DataEntryIntents.SelectedCurrency(

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

                            DataEntryIntents.InputTextWarehouse(inputText,

                                listWarehouse.filter {

                                    it.name!!.contains(inputText, ignoreCase = true)

                                }))

                    },

                    label = { Text("Склад") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuWarehouse)

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

                                        DataEntryIntents.DeleteSelectedWarehouse

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

                                                    DataEntryIntents.SelectedWarehouse(

                                                        item
                                                    )
                                                )

                                            })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.currency,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextCurrency(inputText,

                                listCurrency.filter {

                                    it.name.contains(inputText, ignoreCase = true)
                                }))

                    },

                    label = { Text("Статус") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuCurrency)

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

                                        DataEntryIntents.DeleteSelectedCurrency

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

                                                    DataEntryIntents.SelectedCurrency(

                                                        item
                                                    )
                                                )

                                            })

                                }
                            }
                        }
                    }
                }

            }
        }
    }
}