package com.component

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.component.data_entry.DataEntryComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.network_base_screen.NetworkComponent
import com.util.formatDateTime
import com.viewmodel.SpecificationsIntents
import com.viewmodel.SpecificationsViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

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

                        { viewModel.processIntents(SpecificationsIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Спецификации", color = Color.Black, fontSize = 20.sp)

                }
                Spacer(modifier = Modifier.height(20.dp))

                /*LazyColumn {
                    items(viewModel.state.listContragents){

                        it.entities?.forEach {

                            Text ( "${it.name}  ${it.id}" )


                        }


                    }
                }*/

                LazyColumn {

                    items(viewModel.state.listSpecifications) { item ->

                        Column(modifier = Modifier.fillMaxWidth()) {

                            Spacer(modifier = Modifier.height(10.dp))

                            //0 отмена, 1 новый, 2 подтвержден, 3 оплачен, 5 отгружен

                            when ( item.status ) {

                                0 -> Text("Статус : Отмена", fontSize = 16.sp,

                                    color = Color(0xFF8B0000),

                                    fontWeight = FontWeight.Bold )

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
                                        }?.name?:"Не указано"

                                    else "Не указано"
                                }", fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Дата : ${formatDateTime(item.created_at?:"")}",

                                fontSize = 16.sp)

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Описание : ${item.text ?: "не указано"}", fontSize = 16.sp)

                            Spacer(modifier = Modifier.height(8.dp))

                            Box(

                                modifier = Modifier.height(1.dp).fillMaxWidth()

                                    .background(Color.Gray)
                            )

                        }

                    }
                }

            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                viewModel.processIntents(SpecificationsIntents.OpenCreateDataEntry(scope))

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntry ) {

            DataEntryComponent( listWarehouse = viewModel.state.listWarehouse,

                listProducts = viewModel.state.listProducts,

                listCurrency = viewModel.state.listCurrency,

                onClickBack = {

                    viewModel.processIntents(SpecificationsIntents.BackFromDataEntry) }).Content()

        }

    }

}