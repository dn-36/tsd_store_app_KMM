package com.profile.profile.screens.main_refactor.screens.warehouse.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.project.network.locations_network.model.ResponseItem
import com.project.network.warehouse_network.model.Warehouse
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.cancel
import project.core.resources.down_arrow

class CreateWarehouseComponent (

    val listAllLocations:List<ResponseItem>,

    val onClickUpdate:(scope:CoroutineScope,name:String,localId:String,ui:String) -> Unit,

    val warehouse:Warehouse?,

    val onClickCreate:( scope:CoroutineScope, name:String, localId:String) -> Unit, val index: Int,

                                val locationUpdated:ResponseItem?) : Screen {

    var location by mutableStateOf(if (warehouse != null) warehouse.stores[0]!!.local!!.name else "")

    var title by mutableStateOf(if (warehouse != null) warehouse.stores[0]!!.name else "")

    var expandedLocation by mutableStateOf(false)

    var selectedLocation = mutableStateListOf<ResponseItem>().apply {

        if (warehouse != null && locationUpdated != null) {

            add(locationUpdated)
        }
    }

    var filter by mutableStateOf(listAllLocations)

    @Composable
    override fun Content() {

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .fillMaxHeight(0.7f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp).fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {

                    Column {
                        OutlinedTextField(
                            value = title,
                            onValueChange = {
                                title = it
                            },
                            label = { Text("Название") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp) // Стандартная высота TextField
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            value = location,
                            onValueChange = {inputText ->
                                location = inputText

                                    filter = listAllLocations.filter {
                                        it.name!!.contains(inputText, ignoreCase = true)
                                    }

                            },
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        expandedLocation = !expandedLocation
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.down_arrow),
                                        contentDescription = "Поиск",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            label = { Text("Локация") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 50.dp) // Стандартная высота TextField
                        )
                        if (expandedLocation) {
                            Box(modifier = Modifier.fillMaxWidth().fillMaxHeight(0.7f)) {
                                Card(
                                    modifier = Modifier.fillMaxSize()
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                    backgroundColor = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {}
                                LazyColumn {
                                    itemsIndexed(filter) { index, item ->
                                            Text(item.name!!,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })

                                                { if(selectedLocation.size == 0) {

                                                    selectedLocation.add(item)
                                                }
                                                    else {
                                                        selectedLocation.removeAt(0)

                                                    selectedLocation.add(item)
                                                    }
                                                    expandedLocation = false

                                                    location = ""

                                                }) }

                                }
                            }
                        }

                        LazyColumn (modifier = Modifier.fillMaxWidth()) {
                            items(selectedLocation){item ->
                                    Box(modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                                        .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))){
                                        Text(text = item.name!!,color = Color.White, fontSize = 15.sp, modifier = Modifier.padding(8.dp).align(
                                            Alignment.CenterStart))
                                        Image(painter = painterResource(Res.drawable.cancel),contentDescription = null,
                                            modifier = Modifier.padding(8.dp).size(10.dp).align(Alignment.TopEnd).clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })
                                            {selectedLocation.remove(item)})
                                    }

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if(index == 2) {

                        Button(
                            onClick = {
                                onClickCreate(

                                    scope,

                                    title,

                                    if (selectedLocation.size != 0) selectedLocation[0].id.toString() else ""
                                )
                            },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Создать")
                        }
                    }
                    else if(index == 1) {

                        Button(
                            onClick = {
                                onClickUpdate(

                                    scope,

                                    title,

                                    if (selectedLocation.size != 0) selectedLocation[0].id.toString() else "" ,

                                    warehouse!!.stores[0]!!.ui.toString()
                                )
                            },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Редактировать")
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.03f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ){

            }

        }
    }
}