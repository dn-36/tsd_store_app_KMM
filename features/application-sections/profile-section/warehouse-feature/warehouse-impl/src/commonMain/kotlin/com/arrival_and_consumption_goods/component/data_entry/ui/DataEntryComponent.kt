package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.ui

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.viewmodel.DataEntryIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.data_entry.viewmodel.DataEntryViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ContragentResponseArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.EntityArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.StoreArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.WarehouseArrivalAndConsumption
import com.project.core_app.utils.boxHeight


class DataEntryComponent(

    val updatedText: String,

    val isPush: Int,

    val updatedContragentExpense : ContragentResponseArrivalAndConsumption?,

    val updatedContragentParish : ContragentResponseArrivalAndConsumption?,

    val updatedEntityExpense : ContragentResponseArrivalAndConsumption?,

    val updatedEntityParish: ContragentResponseArrivalAndConsumption?,

    val updatedWarehouse : WarehouseArrivalAndConsumption?,

    val listAllContragents: List<ContragentResponseArrivalAndConsumption>,

    val listAllWarehouse: List<WarehouseArrivalAndConsumption>,

    val onClickBack:() -> Unit,

    val onClickNext: (

        description: String,

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?

    ) -> Unit

) {

    val vm = DataEntryViewModel()

    @Composable
    fun Content() {

        vm.processIntents(DataEntryIntents.SetScreen( updatedText, listAllContragents,

            listAllWarehouse, updatedContragentExpense, updatedContragentParish,

            updatedEntityExpense, updatedEntityParish, updatedWarehouse ))

        val scrollState = rememberScrollState() // Состояние прокрутки

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp).verticalScroll(scrollState)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    if ( isPush == 1 ) {

                        Text("Приход", color = Color.Black, fontSize = 20.sp)

                    }
                    else if ( isPush == 0 ) {

                        Text("Расход", color = Color.Black, fontSize = 20.sp)

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = vm.state.contragentParish,

                    onValueChange = { inputText ->

                        vm.processIntents(DataEntryIntents.TextInputContragentParish ( inputText, listAllContragents.filter {

                            it.name!!.contains(inputText, ignoreCase = true) }))


                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = vm.state.listColorBorderTF[0], // Цвет границы при фокусе
                        unfocusedBorderColor = vm.state.listColorBorderTF[0], // Цвет границы в неактивном состоянии
                        backgroundColor = vm.state.listColorBorderTF[0].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                           vm.processIntents(DataEntryIntents.MenuContragentsParish)

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
                if (vm.state.expandedContragentParish) {

                    Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                        vm.state.filteredContragentParish.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( vm.state.filteredContragentParish ) { index, item ->

                                Text(item.name!!,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            vm.processIntents(DataEntryIntents.SelectContragentParish ( item ))

                                        })
                            }
                        }
                    }
                }

                if ( vm.state.selectedContragentParish != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = vm.state.selectedContragentParish!!.name!!,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )
                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp).align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    vm.processIntents( DataEntryIntents.CancelContragentParish )

                                     })
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(

                        value = vm.state.legalEntityParish,

                        onValueChange = {

                        vm.processIntents( DataEntryIntents.TextInputLegalEntityParish(it) )

                        },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                            unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                            backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                        ),

                        label = { Text("Юр.лицо") },

                        trailingIcon = {

                            IconButton(

                                onClick = {

                                    vm.processIntents(DataEntryIntents.MenuLegalEntityParish)
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

                    if ( vm.state.expandedLegalEntityParish && vm.state.selectedContragentParish!!.entits!!.isNotEmpty() ) {

                        Box(modifier = Modifier.fillMaxWidth().height(100.dp),

                            contentAlignment = Alignment.CenterStart) {

                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}


                            LazyColumn {

                                val validEntities =
                                    vm.state.selectedContragentParish?.entits?.filter { it.name != null }

                                println("checkkkk ${ validEntities }")

                                itemsIndexed( if (validEntities != null && validEntities.isNotEmpty()) vm.state.selectedContragentParish!!.entits!! else

                                    listOf<EntityArrivalAndConsumption>( EntityArrivalAndConsumption(id = 0,name = "Юр.лицо не найдено", ui = "" ) ) ) { index, item ->

                                        Text(item.name!!,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {

                                                    if( item.name != "Юр.лицо не найдено" ) {

                                                        vm.processIntents(

                                                            DataEntryIntents.SelectLegalEntityParish(

                                                                item
                                                            )
                                                        )

                                                    }

                                                })

                                }
                            }
                        }
                    }

                    if( vm.state.selectedLegalEntityParish != null ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                        ) {
                            Text(
                                text = vm.state.selectedLegalEntityParish!!.name!!,
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

                                        vm.processIntents( DataEntryIntents.CancelLegalEntityParish )

                                    })
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = vm.state.contragentExpense,

                    onValueChange = { inputText ->

                        vm.processIntents(DataEntryIntents.TextInputContragentExpense ( inputText, listAllContragents.filter {

                            it.name!!.contains(inputText, ignoreCase = true) }))


                    },
                    label = { Text("Контрагент расход") },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = vm.state.listColorBorderTF[2], // Цвет границы при фокусе
                        unfocusedBorderColor = vm.state.listColorBorderTF[2], // Цвет границы в неактивном состоянии
                        backgroundColor = vm.state.listColorBorderTF[2].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                vm.processIntents(DataEntryIntents.MenuContragentsExpense)

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
                if ( vm.state.expandedContragentExpense ) {

                    Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                        vm.state.filteredContragentExpense.size).dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( vm.state.filteredContragentExpense ) { index, item ->

                                Text(item.name!!,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            vm.processIntents(DataEntryIntents.SelectContragentExpense ( item ))

                                        })
                            }
                        }
                    }

                }
                if ( vm.state.selectedContragentExpense != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = vm.state.selectedContragentExpense!!.name!!,
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )
                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp).align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    vm.processIntents( DataEntryIntents.CancelContragentExpense )

                                })
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(

                        value = vm.state.legalEntityExpense,

                        onValueChange = { inputText ->

                            vm.processIntents(DataEntryIntents.TextInputLegalEntityExpense ( inputText ))
                        },
                        label = { Text("Юр.лицо") },

                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = vm.state.listColorBorderTF[3], // Цвет границы при фокусе
                            unfocusedBorderColor = vm.state.listColorBorderTF[3], // Цвет границы в неактивном состоянии
                            backgroundColor = vm.state.listColorBorderTF[3].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                        ),

                        trailingIcon = {

                            IconButton(

                                onClick = {

                                    vm.processIntents(DataEntryIntents.MenuLegalEntityExpense)

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

                    if ( vm.state.expandedLegalEntityExpense && vm.state.selectedContragentExpense!!.entits!!.isNotEmpty()) {

                        Box(modifier = Modifier.fillMaxWidth().height(100.dp), contentAlignment = Alignment.CenterStart) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}

                            LazyColumn {

                                val validEntities =
                                    vm.state.selectedContragentExpense?.entits?.filter { it.name != null }

                                itemsIndexed(

                                    if (validEntities != null && validEntities.isNotEmpty()) vm.state.selectedContragentExpense!!.entits!! else

                                        listOf <EntityArrivalAndConsumption> ( EntityArrivalAndConsumption(id = 0,name = "Юр.лицо не найдено", ui = "" ))

                                ) { index, item ->


                                        Text(item.name!!,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })

                                                {

                                                    if ( item.name != "Юр.лицо не найдено" ) {

                                                        vm.processIntents(
                                                            DataEntryIntents.SelectLegalEntityExpense(
                                                                item
                                                            )
                                                        )

                                                    }


                                                })

                                }
                            }
                        }
                    }
                    if ( vm.state.selectedLegalEntityExpense != null ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                        ) {
                            Text(
                                text = vm.state.selectedLegalEntityExpense!!.name!!,
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

                                        vm.processIntents( DataEntryIntents.CancelLegalEntityExpense )

                                    })
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = vm.state.warehouse ,

                    onValueChange = { inputText ->

                        vm.processIntents(DataEntryIntents.TextInputWarehouse ( inputText, listAllWarehouse.filter {

                            it.name!!.contains(inputText, ignoreCase = true) }))


                    },

                    label = { Text("Cклад") },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = vm.state.listColorBorderTF[4], // Цвет границы при фокусе
                        unfocusedBorderColor = vm.state.listColorBorderTF[4], // Цвет границы в неактивном состоянии
                        backgroundColor = vm.state.listColorBorderTF[4].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                vm.processIntents(DataEntryIntents.MenuWarehouse)

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
                if (vm.state.expandedWarehouse) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}

                        LazyColumn {

                            itemsIndexed( if (vm.state.filteredWarehouse.size != 0) vm.state.filteredWarehouse

                                else listOf ( WarehouseArrivalAndConsumption( stores = listOf(StoreArrivalAndConsumption( id = 0,

                                name = "Склад не найден" , ui = null )), name = " " ) )

                            ) { index, item ->

                                    Text(item.stores[0]!!.name?:"Нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {

                                                if ( item.stores[0]!!.name != "Склад не найден" ) {

                                                    vm.processIntents(
                                                        DataEntryIntents.SelectWarehouse(
                                                            item
                                                        )
                                                    )
                                                }

                                            })
                            }
                        }
                    }
                }

                if(vm.state.selectedWarehouse != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = vm.state.selectedWarehouse!!.stores[0]!!.name?:"Нет имени",
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

                                    vm.processIntents( DataEntryIntents.CancelWarehouse )

                                })
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = vm.state.description,

                    onValueChange = { inputText ->

                        vm.processIntents(DataEntryIntents.TextInputDescription ( inputText ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.LightGray, // Цвет границы при фокусе
                        unfocusedBorderColor = Color.LightGray, // Цвет границы в неактивном состоянии
                        backgroundColor = Color.LightGray.copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Описание") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp) // Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {

                        vm.processIntents(DataEntryIntents.CheckNullTF)

                        if ( vm.state.onCheck ) {

                            onClickNext(

                                vm.state.description,

                                if (vm.state.selectedLegalEntityParish != null) vm.state.selectedLegalEntityParish!!.id else null,

                                if (vm.state.selectedLegalEntityExpense != null) vm.state.selectedLegalEntityExpense!!.id else null,

                                if (vm.state.selectedContragentExpense != null) vm.state.selectedContragentExpense!!.id else null,

                                if (vm.state.selectedContragentParish != null) vm.state.selectedContragentParish!!.id else null,

                                if (vm.state.selectedWarehouse != null) vm.state.selectedWarehouse!!.stores[0]!!.id else null

                            )
                        }

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