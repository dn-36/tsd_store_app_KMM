package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.components.PlusButton
import com.project.core_app.components.search_component.ui.SearchComponent
import com.project.core_app.network_base_screen.NetworkComponent
import component.data_entry.ui.DataEntryComponent
import kotlinx.coroutines.CoroutineScope
import model.ServiceItemCreateCRMModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.update_pencil
import util.formatDateTime
import viewmodel.CRMIntents
import viewmodel.CRMViewModel

class CRMComponent ( override val viewModel: CRMViewModel ) : NetworkComponent {

    @Composable

    override fun Component() {

        val scope = rememberCoroutineScope()

        viewModel.processIntents(CRMIntents.SetScreen(scope))

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.Back) }
                    )

                    Spacer(modifier = Modifier.width(10.dp))


                    Text("CRM", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement = Arrangement.SpaceAround
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.SelectTypeCRM(1)) }) {

                        Text("Входящие", fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.height(1.dp).width(100.dp)

                                .background(
                                    if (viewModel.state.isIncoming) Color.Gray
                                    else Color.Transparent
                                )
                        )

                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally,

                        modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(CRMIntents.SelectTypeCRM(2)) }) {

                        Text("Исходящие", fontSize = 18.sp)

                        Spacer(modifier = Modifier.height(8.dp))

                        Box(
                            modifier = Modifier.height(1.dp).width(110.dp)

                                .background(
                                    if (viewModel.state.isOutgoing) Color.Gray
                                    else Color.Transparent
                                )
                        )

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text("Для поиска введите имя юр.лица исполнителя", color = Color.Gray,

                    fontSize = 16.sp)

                Spacer(modifier = Modifier.height(8.dp))

                SearchComponent( onValueChange = { text -> viewModel.processIntents(

                    CRMIntents.InputTextSearchComponent(text)) } ).Content()

                if (viewModel.state.isIncoming) {

                    LazyColumn {

                        items(viewModel.state.listFilteredIncomingCRM) { item ->

                            Column(modifier = Modifier.fillMaxWidth()) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    "Клиент/Проект : ${
                                        
                                        if (item.entity != null)

                                            item.entity.name else "Не указано"
                                    } ${
                                        if (item.groupentits != null)

                                            "/ ${item.groupentits.name}" else ""
                                    } / ${

                                        if (item.projects != null)

                                            item.projects.name else "Не указано"
                                        
                                    } ", fontSize = 16.sp, modifier = Modifier

                                        .fillMaxWidth(0.9f)
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    "Юр.лицо исполнитель : ${
                                        
                                        if (item.entity_our != null)

                                            item.entity_our.name else "Не указано"
                                        
                                    }", fontSize = 16.sp
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Дата : ${formatDateTime(item.created_at)}",

                                    fontSize = 16.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                Text("Прайс : ${item.price ?: 0}", fontSize = 16.sp)

                                Spacer(modifier = Modifier.height(8.dp))

                                when ( item.statusid ) {

                                    0 -> Text("Статус : Завершена", fontSize = 16.sp,

                                        color = Color(0xFF228B22),

                                        fontWeight = FontWeight.Bold )

                                    1 -> Text("Статус : Активна", fontSize = 16.sp)

                                    2 -> Text("Статус : В работе", fontSize = 16.sp)

                                    3 -> Text("Статус : Срочная", fontSize = 16.sp)

                                    4 -> Text("Статус : Не выполнена", fontSize = 16.sp)

                                    5 -> Text("Статус : Выполнена не до конца",

                                        fontSize = 16.sp)

                                    6 -> Text("Статус : Отложена", fontSize = 16.sp)

                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Box(
                                    modifier = Modifier.height(1.dp).fillMaxWidth()

                                        .background(Color.LightGray)
                                )

                            }

                        }
                    }

                } else if (viewModel.state.isOutgoing) {

                    LazyColumn {

                        itemsIndexed(

                            viewModel.state.listFilteredOutgoingCRM) { index,item ->

                            Box (modifier = Modifier.fillMaxWidth().pointerInput(true) {

                                detectTapGestures(

                                    onPress = {
                                        if ( viewModel.state.listAlphaTools.size > index &&

                                            viewModel.state.listAlphaTools[index] == 1f ) {

                                            viewModel.processIntents(CRMIntents.OnePressItem)
                                        }
                                    },

                                    onLongPress = {

                                        viewModel.processIntents(CRMIntents.LongPressItem(index))

                                    },
                                )
                            }) {

                                Column() {

                                    Text(

                                        "Клиент/Проект : ${
                                            
                                            if (item.entity != null)

                                                item.entity.name else "Не указано"
                                        } ${ if (item.groupentits != null)

                                                "/ ${item.groupentits.name}" else ""
                                            
                                        } / ${ if (item.projects != null)

                                                item.projects.name else "Не указано"
                                            
                                        }", fontSize = 16.sp, modifier = Modifier

                                            .fillMaxWidth(0.9f)
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        "Юр.лицо исполнитель : ${
                                            
                                            if (item.entity_our != null)

                                                item.entity_our.name else "Не указано"
                                            
                                        }", fontSize = 16.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Дата : ${formatDateTime(item.created_at)}",

                                        fontSize = 16.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text("Прайс : ${item.price ?: 0}",

                                        fontSize = 16.sp)

                                    Spacer(modifier = Modifier.height(8.dp))

                                    when (item.statusid) {

                                        0 -> Text("Статус : Завершена", fontSize = 16.sp,

                                            color = Color(0xFF228B22),

                                            fontWeight = FontWeight.Bold)

                                        1 -> Text("Статус : Активна", fontSize = 16.sp)

                                        2 -> Text("Статус : В работе", fontSize = 16.sp)

                                        3 -> Text("Статус : Срочная", fontSize = 16.sp)

                                        4 -> Text("Статус : Не выполнена", fontSize = 16.sp)

                                        5 -> Text("Статус : Выполнена не до конца",

                                            fontSize = 16.sp)

                                        6 -> Text("Статус : Отложена", fontSize = 16.sp)

                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Box(
                                        modifier = Modifier.height(1.dp).fillMaxWidth()

                                            .background(Color.LightGray)
                                    )

                                }

                                if ( viewModel.state.listAlphaTools.size > index &&

                                    viewModel.state.listAlphaTools[index] == 1f ) {

                                    Image(painter = painterResource(Res.drawable.update_pencil),
                                        contentDescription = null,
                                        modifier = Modifier.padding(8.dp)
                                            .size(17.dp).align(Alignment.TopEnd)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember {

                                                    MutableInteractionSource()
                                                })
                                            {
                                                viewModel.processIntents(

                                                    CRMIntents.OpenUpdateDataEntryComponent(
                                                        item,
                                                        scope
                                                    )
                                                )
                                            })

                                }

                            }

                        }
                    }

                }


            }

            Box(modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)) {

                PlusButton {

                    viewModel.processIntents(CRMIntents.OpenCreateDataEntryComponent( scope ))

                }

            }

        }

        if ( viewModel.state.isVisibilityDataEntryComponent == 1f ) {

            DataEntryComponent( onClickBack = {

                viewModel.processIntents(CRMIntents.BackToCRMComponent) },

                listSpecifications = viewModel.state.listSpecifications,

                listServices = viewModel.state.listServices,

                listLocations = viewModel.state.listLocations,

                listContragents = viewModel.state.listContragents,

                listEmployee = viewModel.state.listEmployee,

                listCargo = viewModel.state.listCargo,

                listGroupEntity = viewModel.state.listGroupEntity,

                listProjects = viewModel.state.listProjects,

                listProducts = viewModel.state.listProducts,

                item = viewModel.state.updateItem,

                onClickCreate = { scope: CoroutineScope, serviceId: Int?, statusPay: Int?,

                                  verifyPay: Int?, task: String?, to_local_id:Int?,

                                  group_entity_id:Int?, from_local_id:Int?, status: String?,

                                  price: String?, arendaId: Int?, specificationId: Int?,

                                  projectId: Int?, entityId: Int?, ourEntityId: Int?, text: String?,

                                  statusId: Int?, items: List<ServiceItemCreateCRMModel>? ->

                    viewModel.processIntents( CRMIntents.CreateCRM( scope, serviceId, statusPay,

                        verifyPay, task, to_local_id, group_entity_id, from_local_id, status, price,

                        arendaId, specificationId, projectId, entityId, ourEntityId, text,

                         statusId, items ))

                }, onClickUpdate = { scope: CoroutineScope, ui: String, serviceId: Int?,

                                     statusPay: Int?, verifyPay: Int?, task: String?,

                                     to_local_id: Int?, group_entity_id: Int?, from_local_id: Int?,

                                     status: String?, price: String?, arendaId: Int?,

                                     specificationId: Int?, projectId: Int?, entityId: Int?,

                                     ourEntityId: Int?, text: String?, statusId: Int?,

                                     items: List<ServiceItemCreateCRMModel>? ->

                    viewModel.processIntents( CRMIntents.UpdateCRM( scope, ui, serviceId, statusPay,

                        verifyPay, task, to_local_id, group_entity_id, from_local_id, status, price,

                        arendaId, specificationId, projectId, entityId, ourEntityId, text,

                        statusId, items ))

                }).Content()

        }
    }
}