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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.network.contragent_network.model.ContragentResponse
import com.project.network.contragent_network.model.Entity
import com.project.network.warehouse_network.model.Warehouse
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryComponent(

    val listAllContragents: List<ContragentResponse>,

    val listAllWarehouse: List<Warehouse>,

    val onClickBack:() -> Unit,

    val onClickNext: (

        idLegalEntityParish: Int?,

        idLegalEntityExpense: Int?,

        idContragentExpense: Int?,

        idContragentParish: Int?,

        idWarehouse: Int?

    ) -> Unit

) {

    var counterpartyParish by mutableStateOf("")

    var legalEntityParish by mutableStateOf("")

    var expandedCounterpartyParish by mutableStateOf(false)

    var expandedLegalEntityParish by mutableStateOf(false)

    var counterpartyExpense by mutableStateOf("")

    var legalEntityExpense by mutableStateOf("")

    var expandedCounterpartyExpense by mutableStateOf(false)

    var expandedLegalEntityExpense by mutableStateOf(false)

    var warehouse by mutableStateOf("")

    var expandedWarehouse by mutableStateOf(false)

    var contragentParish by mutableStateOf<ContragentResponse?>(null)

    var contragentExpense by mutableStateOf<ContragentResponse?>(null)

    var selectedLegalEntityParish by mutableStateOf<Entity?>(null)

    var selectedLegalEntityExpense by mutableStateOf<Entity?>(null)

    var selectedWarehouse by mutableStateOf<Warehouse?>(null)

    @Composable
    fun Content() {

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

                    Text("Приход", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = counterpartyParish,
                    onValueChange = {
                        counterpartyParish = it

                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {

                                expandedCounterpartyParish = !expandedCounterpartyParish
                                expandedWarehouse = false
                                expandedCounterpartyExpense = false
                                expandedLegalEntityExpense = false
                                expandedLegalEntityParish = false

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
                if (expandedCounterpartyParish) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listAllContragents) { index, item ->

                                Text(item.name!!,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            expandedCounterpartyParish = false

                                            contragentParish = item

                                        })
                            }
                        }
                    }
                }

                if (contragentParish != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = contragentParish!!.name!!,
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
                                { contragentParish = null

                                selectedLegalEntityParish = null })
                    }


                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(

                        value = legalEntityParish,

                        onValueChange = {

                            legalEntityParish = it

                        },
                        label = { Text("Юр.лицо") },
                        trailingIcon = {
                            IconButton(
                                onClick = {

                                    expandedLegalEntityParish = !expandedLegalEntityParish
                                    expandedWarehouse = false
                                    expandedCounterpartyExpense = false
                                    expandedLegalEntityExpense = false
                                    expandedCounterpartyParish = false
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

                    if (expandedLegalEntityParish && contragentParish!!.entits!!.isNotEmpty()) {

                        Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}

                            LazyColumn {

                                val validEntities =
                                    contragentParish!!.entits!!.filter { it.name != null }

                                itemsIndexed(if(validEntities.isNotEmpty()) contragentParish!!.entits!! else listOf(contragentParish!!.entits!![0])) { index, item ->

                                    if (validEntities.isNotEmpty()) {
                                        Text(item.name!!,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {

                                                    expandedLegalEntityParish = false

                                                    selectedLegalEntityParish = item
                                                })
                                    } else {
                                        Text("Юр.лицо не найдено",
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })
                                                {})
                                    }
                                }
                            }
                        }
                    }
                    if(selectedLegalEntityParish != null) {
                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                        ) {
                            Text(
                                text = selectedLegalEntityParish!!.name!!,
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
                                    { selectedLegalEntityParish = null })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = counterpartyExpense,
                    onValueChange = { inputText ->

                        counterpartyExpense = inputText

                    },
                    label = { Text("Контрагент расход") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                expandedCounterpartyExpense = !expandedCounterpartyExpense
                                expandedWarehouse = false
                                expandedCounterpartyParish = false
                                expandedLegalEntityExpense = false
                                expandedLegalEntityParish = false
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
                if (expandedCounterpartyExpense) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listAllContragents) { index, item ->

                                Text(item.name!!,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {
                                            expandedCounterpartyExpense = false

                                            contragentExpense = item

                                        })
                            }
                        }
                    }

                }
                if (contragentExpense != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = contragentExpense!!.name!!,
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
                                { contragentExpense = null

                                selectedLegalEntityExpense = null})
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = legalEntityExpense,
                        onValueChange = { inputText ->

                            legalEntityExpense = inputText
                        },
                        label = { Text("Юр.лицо") },
                        trailingIcon = {
                            IconButton(
                                onClick = {

                                    expandedLegalEntityExpense = !expandedLegalEntityExpense
                                    expandedLegalEntityParish = false
                                    expandedWarehouse = false
                                    expandedCounterpartyParish = false
                                    expandedCounterpartyExpense = false

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

                    if (expandedLegalEntityExpense && contragentExpense!!.entits!!.isNotEmpty()) {

                        Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                            Card(
                                modifier = Modifier.fillMaxSize()
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                backgroundColor = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) {}

                            LazyColumn {

                                val validEntities =
                                    contragentExpense!!.entits!!.filter { it.name != null }

                                itemsIndexed(
                                    if (validEntities.isNotEmpty()) contragentExpense!!.entits!! else listOf(
                                        contragentExpense!!.entits!![0]
                                    )
                                ) { index, item ->

                                    if (validEntities.isNotEmpty()) {

                                        Text(item.name!!,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })

                                                {
                                                    expandedLegalEntityExpense = false

                                                    selectedLegalEntityExpense = item

                                                })
                                    } else {

                                        Text("Юр.лицо не найдено",
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })

                                                {})
                                    }
                                }
                            }
                        }
                    }
                    if (selectedLegalEntityExpense != null) {

                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier.padding(vertical = 5.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                        ) {
                            Text(
                                text = selectedLegalEntityExpense!!.name!!,
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
                                    { selectedLegalEntityExpense = null })
                        }

                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = warehouse,
                    onValueChange = { inputText ->

                        warehouse = inputText

                    },
                    label = { Text("Cклад") },

                    trailingIcon = {
                        IconButton(
                            onClick = {
                                expandedWarehouse = !expandedWarehouse
                                expandedCounterpartyExpense = false
                                expandedCounterpartyParish = false
                                expandedLegalEntityParish = false
                                expandedLegalEntityExpense = false
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
                if (expandedWarehouse) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(listAllWarehouse) { index, item ->
                                Text(item.stores[0]!!.name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            expandedWarehouse = false

                                            selectedWarehouse = item

                                        })
                            }
                        }
                    }
                }

                if(selectedWarehouse != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = selectedWarehouse!!.stores[0]!!.name!!,
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
                                { selectedWarehouse = null })
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { onClickNext(

                        selectedLegalEntityParish!!.id,

                        selectedLegalEntityExpense!!.id,

                        contragentExpense!!.id,

                        contragentParish!!.id,

                        selectedWarehouse!!.id ) },

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