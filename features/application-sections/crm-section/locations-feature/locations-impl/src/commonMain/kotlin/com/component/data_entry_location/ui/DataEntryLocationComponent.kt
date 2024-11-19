package com.component.data_entry_location.ui

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.component.data_entry_location.viewmodel.DataEntryLocationIntents
import com.component.data_entry_location.viewmodel.DataEntryLocationViewModel
import com.model.ContragentsResponseModel
import com.model.LocationResponseModel
import com.project.core_app.utils.boxHeight
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryLocationComponent (

    val onClickBack:() -> Unit,

    val onClickCreate:(

        name: String?, email: String?, phone: String?,

        default: Int?, text: String?,

        telegram: String?, whatsapp: String?, wechat: String?,

        point: List<Double>?, adres: String, contragent_id: Int,

        entity_id: Int, workers: List<Int>, langs: List<Int> ) -> Unit,

    val onClickUpdate:(

        name: String?, email: String?, phone: String?,

        default: Int?, text: String?,

        telegram: String?, whatsapp: String?, wechat: String?,

        point: List<Double>?, adres: String, contragent_id: Int,

        entity_id: Int, workers: List<Int>, langs: List<Int> ) -> Unit,

    val listContragents: List<ContragentsResponseModel>,

    val item: LocationResponseModel?

) {

    val viewModel = DataEntryLocationViewModel()

    @Composable

    fun Content () {

        val scroll = rememberScrollState()

        viewModel.processIntents(DataEntryLocationIntents.SetScreen(item, listContragents))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp).verticalScroll(scroll)) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("Локации", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(

                    value = viewModel.state.name,

                    onValueChange = {

                    viewModel.processIntents(DataEntryLocationIntents.InputTextTitle(it))

                    },

                    label = { Text("Название", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.contragent,

                    onValueChange = { inputText ->

                    viewModel.processIntents(

                       DataEntryLocationIntents.InputTextContragent( inputText,

                           listContragents.filter {

                           it.name!!.contains(inputText, ignoreCase = true)

                       } ))

                    },

                    label = { Text("Контрагент") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryLocationIntents.MenuContragents)

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

                if (viewModel.state.selectedContragent != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedContragent!!.name?:"Нет имени",
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

                                { viewModel.processIntents(

                                    DataEntryLocationIntents.DeleteSelectedContragent) })
                    }
                }

                if ( viewModel.state.expendedContragents ) {

                    Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                        viewModel.state.filteredListContragents.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.filteredListContragents)

                            { index, item ->


                                    Text( item.name?:"Нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                            .padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(

                                                    DataEntryLocationIntents.SelectContragent(item))

                                            })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.entity,

                    onValueChange = { inputText ->

                    viewModel.processIntents(DataEntryLocationIntents.InputTextEntity(inputText,

                        listContragents.flatMap { contragent ->

                            contragent.entities.filter {

                                val entityName = it.name.orEmpty()

                                entityName.contains(inputText, ignoreCase = true)
                            }
                        }
                    ))

                    },

                    label = { Text("Юр.лицо") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryLocationIntents.MenuEntity)

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

                if (viewModel.state.selectedEntity != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                        Text(
                            text = viewModel.state.selectedEntity!!.name?:"Нет имени",
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

                                { viewModel.processIntents(

                                    DataEntryLocationIntents.DeleteSelectedEntity) })
                    }
                }

                if ( viewModel.state.expendedEntity ) {

                    Box(modifier = Modifier.fillMaxWidth().height( boxHeight(

                        viewModel.state.filteredListEntity.size).dp)) {

                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed(viewModel.state.filteredListEntity)

                            { index, item ->


                                Text( item.name?:"Нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f)
                                        .padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение затемнения
                                            interactionSource = remember {

                                                MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryLocationIntents.SelectEntity(item))

                                        })

                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.phone,

                    onValueChange = {


                    viewModel.processIntents(DataEntryLocationIntents.InputTextPhone(it))

                    },

                    label = { Text("Телефон", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.email,

                    onValueChange = {

                     viewModel.processIntents(DataEntryLocationIntents.InputTextEmail(it))

                    },

                    label = { Text("Email", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.telegram,

                    onValueChange = {

                     viewModel.processIntents(DataEntryLocationIntents.InputTextTelegram(it))

                    },

                    label = { Text("Telegram", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.weChat,

                    onValueChange = {

                    viewModel.processIntents(DataEntryLocationIntents.InputTextWeChat(it))

                    },

                    label = { Text("WeChat", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.whatsApp,

                    onValueChange = {

                    viewModel.processIntents(DataEntryLocationIntents.InputTextWhatsApp(it))

                    },

                    label = { Text("WhatsApp", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.address,

                    onValueChange = {

                        viewModel.processIntents(DataEntryLocationIntents.InputTextAddress(it))

                    },

                    label = { Text("Адрес", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.other,

                    onValueChange = {

                    viewModel.processIntents(DataEntryLocationIntents.InputTextOther(it))

                    },

                    label = { Text("Прочее", fontSize = 15.sp) },

                    textStyle = TextStyle(fontSize = 15.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                if ( item == null ) {

                    Button(

                        onClick = {

                            onClickCreate(

                                viewModel.state.name,

                                viewModel.state.email, viewModel.state.phone,

                                null, viewModel.state.other,

                                viewModel.state.telegram, viewModel.state.whatsApp,

                                viewModel.state.weChat, null,

                                viewModel.state.address,

                                viewModel.state.selectedContragent?.id ?: 0,

                                viewModel.state.selectedEntity?.id ?: 0,

                                emptyList(), emptyList()
                            )

                        },

                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()

                    ) {

                        Text(text = "Создать")

                    }
                }

                else {

                    Button(

                        onClick = {

                            onClickUpdate(

                                viewModel.state.name,

                                viewModel.state.email, viewModel.state.phone,

                                null, viewModel.state.other,

                                viewModel.state.telegram, viewModel.state.whatsApp,

                                viewModel.state.weChat, null,

                                viewModel.state.address,

                                viewModel.state.selectedContragent?.id ?: 0,

                                viewModel.state.selectedEntity?.id ?: 0,

                                emptyList(), emptyList()
                            )

                        },

                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()

                    ) {

                        Text(text = "Редактировать")

                    }

                }

            }
        }
    }
}