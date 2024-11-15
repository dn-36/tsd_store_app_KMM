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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.DeleteComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.network_base_screen.NetworkComponent
import com.viewmodel.LocationsIntents
import com.viewmodel.LocationsViewModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil

class LocationsComponent ( override val viewModel: LocationsViewModel) : NetworkComponent {

    @Composable

    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(LocationsIntents.SetScreen(scope))

        Box( modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(LocationsIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Локации", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn {

                    items(viewModel.state.listLocations){

                        Box () {
                            Column(modifier = Modifier.fillMaxWidth()) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    "Название : ${it.name?:"Не указано"}", fontSize = 16.sp,

                                    modifier = Modifier.fillMaxWidth(0.9f)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Адрес : ${it.adres?:"Не указано"}",

                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Контрагент : ${it.contragent?.name?: "Не указано"}", fontSize = 16.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(

                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.Gray)
                                )

                            }

                            Row(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {

                                Image(painter = painterResource(Res.drawable.update_pencil),
                                    contentDescription = null,
                                    modifier = Modifier.size(17.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {})


                                Spacer(modifier = Modifier.width(20.dp))

                                Image(painter = painterResource(Res.drawable.cancel),
                                    contentDescription = null,
                                    modifier = Modifier.size(15.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        { viewModel.processIntents(

                                            LocationsIntents.OpenDeleteComponent(it)) })

                            }

                        }

                    }

                }

            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    viewModel.processIntents(LocationsIntents.OpenCreateDataEntryComponent(scope))

                }

            }

        }

        if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent(

                name = "локацию",

                onClickDelete = { scope ->

                    viewModel.processIntents(LocationsIntents.DeleteLocation(scope))

                },

                onClickNo = { viewModel.processIntents(LocationsIntents.NoDelete) }

            ).Content()

        }

        else if ( viewModel.state.isVisibilityDataEntry ) {

            DataEntryComponent( onClickBack = {

                viewModel.processIntents(LocationsIntents.BackFromDataEntry)

            }).Content()

        }

    }

}