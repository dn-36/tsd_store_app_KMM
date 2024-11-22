package com.component

import ContragentsScreensApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.LocationsScreenApi
import com.component.data_entry_location.ui.DataEntryLocationComponent
import com.project.core_app.components.delete_component.DeleteComponent
import com.project.core_app.components.PlusButton
import com.project.core_app.components.menu_bottom_bar_contragents.ui.MenuBottomBarContragents
import com.project.core_app.components.menu_bottom_bar_contragents.viewmodel.MenuBottomBarContragentsSection
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import com.viewmodel.LocationsIntents
import com.viewmodel.LocationsViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.mp.KoinPlatform
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.update_pencil

class LocationsComponent ( override val viewModel: LocationsViewModel ) : NetworkComponent {

    @Composable

    override fun Component() {

        val contragentsScreen: ContragentsScreensApi = KoinPlatform.getKoin().get()

        val locationsScreen: LocationsScreenApi = KoinPlatform.getKoin().get()

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

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    LocationsIntents.InputTextSearchComponent(text)) } ).Content()

                LazyColumn ( modifier = Modifier.fillMaxHeight(0.85f) ) {

                    itemsIndexed(

                        viewModel.state.listFilteredLocations){index,item ->

                        Box ( modifier = Modifier.pointerInput(true) {

                            detectTapGestures(

                                onPress = {
                                    if ( viewModel.state.listAlphaTools.size > index &&

                                        viewModel.state.listAlphaTools[index] == 1f ) {

                                        viewModel.processIntents(LocationsIntents.OnePressItem)
                                    }
                                },

                                onLongPress = {

                                    viewModel.processIntents(LocationsIntents.LongPressItem(index))

                                },
                            )
                        }) {
                            Column(modifier = Modifier.fillMaxWidth()) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    "Название : ${item.name?:"Не указано"}", fontSize = 16.sp,

                                    modifier = Modifier.fillMaxWidth(0.85f)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Адрес : ${item.adres?:"Не указано"}",

                                    fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Контрагент : ${item.contragent?.name?: "Не указано"}", fontSize = 16.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(

                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.LightGray)
                                )

                            }

                            if ( viewModel.state.listAlphaTools.size > index &&

                                viewModel.state.listAlphaTools[index] == 1f ) {

                                Row(modifier = Modifier.align(Alignment.TopEnd).padding(8.dp)) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {
                                                viewModel.processIntents(

                                                    LocationsIntents.OpenUpdateDataEntryComponent(

                                                        scope, item
                                                    )
                                                )
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

                                                    LocationsIntents.OpenDeleteComponent(item)
                                                )
                                            })

                                }
                            }

                        }

                    }

                }

            }

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

                PlusButton {

                    viewModel.processIntents(LocationsIntents.OpenCreateDataEntryComponent(scope))

                }

                MenuBottomBarContragents().init(

                    contragentsScreen.contragents(),

                    locationsScreen.locations()

                ).Content(MenuBottomBarContragentsSection.LOCATIONS)
            }

        }

        if ( viewModel.state.isVisibilityDeleteComponent ) {

            DeleteComponent(

                name = "локацию",

                onClickDelete = {

                    viewModel.processIntents(LocationsIntents.DeleteLocation(scope))

                },

                onClickNo = { viewModel.processIntents(LocationsIntents.NoDelete) }

            ).Content()

        }

        else if ( viewModel.state.isVisibilityDataEntry ) {

            DataEntryLocationComponent( onClickBack = {

                viewModel.processIntents(LocationsIntents.BackFromDataEntry)

            }, onClickCreate = { name, email, phone, default, text, telegram, whatsapp, wechat,

                                 point, adres, contragent_id, entity_id, workers, langs ->

                               viewModel.processIntents(LocationsIntents.CreateLocation( scope,

                                   name, email, phone, default, text, telegram, whatsapp, wechat,

                                   point, adres, contragent_id, entity_id, workers, langs))
            },

                onClickUpdate = { name, email, phone, default, text, telegram, whatsapp, wechat,

                                  point, adres, contragent_id, entity_id, workers, langs ->

                                viewModel.processIntents(LocationsIntents.UpdateLocation( scope,

                                    name, email, phone, default, text, telegram, whatsapp, wechat,

                                    point, adres, contragent_id, entity_id, workers, langs ))
                },

                listContragents = viewModel.state.listContragents,

                item = viewModel.state.updateLocation ).Content()

        }

    }

}