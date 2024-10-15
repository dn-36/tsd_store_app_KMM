package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.viewmodel.ArrivalAndConsumptionViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.down_arrow

class ArrivalGoodsComponent (val vm : ArrivalAndConsumptionViewModel) :Screen{
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
            Column(modifier = Modifier.padding(16.dp),) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(painter = painterResource(Res.drawable.back),contentDescription = null,
                        modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Text("Приемка", color = Color.Black, fontSize = 20.sp)

                }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = vm.arrivalAndConsumptionState.counterpartyParish,
                        onValueChange = {
                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                counterpartyParish = it
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                        expandedCounterpartyParish = !vm.arrivalAndConsumptionState.expandedCounterpartyParish
                                    )
                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.down_arrow),
                                    contentDescription = "Поиск",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        label = { Text("Контрагент приход") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 40.dp) // Стандартная высота TextField
                    )
                if (vm.arrivalAndConsumptionState.expandedCounterpartyParish) {
                    Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("Наш")) { index, item ->
                                Text(item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(

                                                expandedCounterpartyParish = false,

                                                )

                                        })
                            }
                        }
                    }
                }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = vm.arrivalAndConsumptionState.legalEntityParish,
                        onValueChange = {
                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                legalEntityParish = it
                            )
                        },
                        label = { Text("Юр.лицо") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                        expandedLegalEntityParish = !vm.arrivalAndConsumptionState.expandedLegalEntityParish
                                    )
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
                            .heightIn(min = 40.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { }// Стандартная высота TextField
                    )
                    if (vm.arrivalAndConsumptionState.expandedLegalEntityParish) {
                        Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(listOf("Наше")) { index, item ->
                                    Text(item,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })
                                            {
                                                vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                                    expandedLegalEntityParish = false
                                                )
                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = vm.arrivalAndConsumptionState.counterpartyExpense,
                        onValueChange = {inputText ->
                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                counterpartyExpense = inputText
                            )
                        },
                        label = { Text("Контрагент расход") },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                        expandedCounterpartyExpense = !vm.arrivalAndConsumptionState.expandedCounterpartyExpense
                                    )
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
                            .heightIn(min = 40.dp)
                            .clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { }// Стандартная высота TextField
                    )
                    if (vm.arrivalAndConsumptionState.expandedCounterpartyExpense) {
                        Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}
                            LazyColumn {
                                itemsIndexed(listOf("Наш")) { index, item ->
                                    Text(item,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(

                                                    expandedCounterpartyExpense = false,

                                                )

                                            })
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = vm.arrivalAndConsumptionState.legalEntityExpense,
                    onValueChange = {inputText ->
                        vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                            legalEntityExpense = inputText
                        )
                    },
                    label = { Text("Юр.лицо") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                    expandedLegalEntityExpense = !vm.arrivalAndConsumptionState.expandedLegalEntityExpense
                                )
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
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { }// Стандартная высота TextField
                )
                if (vm.arrivalAndConsumptionState.expandedLegalEntityExpense) {
                    Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("Наше")) { index, item ->
                                Text(item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(

                                                expandedLegalEntityExpense = false,

                                                )

                                        })
                            }
                        }
                    }

                }
                    Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = vm.arrivalAndConsumptionState.counterpartyExpense,
                    onValueChange = {inputText ->
                        vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                            warehouse = inputText
                        )
                    },
                    label = { Text("Cклад") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(
                                    expandedWarehouse = !vm.arrivalAndConsumptionState.expandedWarehouse
                                )
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
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { }// Стандартная высота TextField
                )
                if (vm.arrivalAndConsumptionState.expandedWarehouse) {
                    Box(modifier = Modifier.fillMaxWidth().height(60.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listOf("Наш")) { index, item ->
                                Text(item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            vm.arrivalAndConsumptionState = vm.arrivalAndConsumptionState.copy(

                                                expandedWarehouse = false,

                                                )

                                        })
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .padding(10.dp)
                    .clip(RoundedCornerShape(70.dp))
                    .height(40.dp)
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.9f)
            ) {
                Text(text = "Далее")
            }
        }
    }
}