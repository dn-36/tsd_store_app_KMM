package component.data_entry.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.core_app.utils.boxHeight
import component.data_entry.viewmodel.DataEntryIntents
import component.data_entry.viewmodel.DataEntryViewModel
import kotlinx.coroutines.CoroutineScope
import model.ApiResponseCRMModel
import model.CargoResponseModel
import model.ContragentResponseModel
import model.GroupEntityResponseModel
import model.LocationResponseModel
import model.ProductModel
import model.ProjectResponseModel
import model.ServiceItemCreateCRMModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel
import model.ValDetailModel
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.down_arrow

class DataEntryComponent (

    val onClickBack: () -> Unit,

    val listSpecifications: List<SpecificResponseModel>,

    val listServices: List<ServiceResponseModel>,

    val listEmployee: List<UserCRMModel>,

    val listContragents: List<ContragentResponseModel>,

    val listLocations: List<LocationResponseModel>,

    val listCargo: List<CargoResponseModel>,

    val listGroupEntity: List<GroupEntityResponseModel>,

    val listProjects: List<ProjectResponseModel>,

    val listProducts: List<ProductModel>,

    val item: ApiResponseCRMModel?,

    val onClickCreate: (

        scope: CoroutineScope,

        serviceId: Int?,

        statusPay: Int?,

        verifyPay: Int?,

        task: String?,

        to_local_id:Int?,

        group_entity_id:Int?,

        from_local_id:Int?,

        status: String?,

        price: String?,

        arendaId: Int?,

        specificationId: Int?,

        projectId: Int?,

        entityId: Int?,

        ourEntityId: Int?,

        text: String?,

        statusId: Int?,

        items: List<ServiceItemCreateCRMModel>?

    ) -> Unit,

    val onClickUpdate: (

        scope: CoroutineScope,

        ui:String,

        serviceId: Int?,

        statusPay: Int?,

        verifyPay: Int?,

        task: String?,

        to_local_id:Int?,

        group_entity_id:Int?,

        from_local_id:Int?,

        status: String?,

        price: String?,

        arendaId: Int?,

        specificationId: Int?,

        projectId: Int?,

        entityId: Int?,

        ourEntityId: Int?,

        text: String?,

        statusId: Int?,

        items: List<ServiceItemCreateCRMModel>?

    ) -> Unit

) {

    val viewModel = DataEntryViewModel()

    @Composable

    fun Content () {

        viewModel.processIntents(DataEntryIntents.SetScreen ( listSpecifications, listServices,

            listEmployee, listContragents, listLocations, listCargo, listGroupEntity,

            listProjects, item ))

        val scrollState = rememberScrollState() // Состояние прокрутки

        val scope = rememberCoroutineScope()

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

                    if (viewModel.state.selectedService == null) {

                        Text("Создание", color = Color.Black, fontSize = 20.sp)

                    }
                    else {

                        Text("Редактирование", color = Color.Black, fontSize = 20.sp)

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField (

                    value = viewModel.state.service,

                    onValueChange = { inputText ->

                       viewModel.processIntents(DataEntryIntents.InputTextService( inputText,

                           listServices.filter {

                               it.name!!.contains(inputText, ignoreCase = true) }))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = viewModel.state.borderServiceColor, // Цвет границы при фокусе
                        unfocusedBorderColor = viewModel.state.borderServiceColor, // Цвет границы в неактивном состоянии
                        backgroundColor = viewModel.state.containerServiceColor.copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Услуги") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                if ( item == null ) {

                                    viewModel.processIntents(DataEntryIntents.MenuServices)

                                }

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

                if ( viewModel.state.selectedService != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedService!!.name?:"нет имени",
                            color = Color.White,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(8.dp).align(
                                Alignment.CenterStart
                            )
                        )

                        if ( item == null ) {

                            Image(painter = painterResource(Res.drawable.cancel),
                                contentDescription = null,
                                modifier = Modifier.padding(8.dp).size(10.dp)
                                    .align(Alignment.TopEnd)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })

                                    {

                                        viewModel.processIntents(DataEntryIntents.DeleteSelectedService)

                                    })
                        }
                    }
                }

                if ( viewModel.state.expendedService ) {

                    Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                        viewModel.state.filteredListServices.size).dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListServices )

                            { index, item ->

                                Text(item.name?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectService( item, listContragents ))

                                        })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(

                    enabled = false,

                    value = "",

                    onValueChange = { inputText ->

                    },

                    label = { Text("Оплачено", color = Color.Gray) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        {  viewModel.processIntents(DataEntryIntents.MenuPaidFor) }// Стандартная высота TextField
                )
                if ( viewModel.state.selectedPaidFor != null &&

                    viewModel.state.selectedPaidFor!!.first != "" ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedPaidFor!!.first,
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

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedPaidFor)

                                })
                    }
                }

                if ( viewModel.state.expendedPaidFor ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed ( listOf("Не оплачено","оплачено") )

                            { index, item ->

                                Text( text = item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryIntents.SelectPaidFor( item,index ))

                                        })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    enabled = false,

                    value = "",

                    onValueChange = { inputText ->

                    },

                    label = { Text("Платеж подтвержден", color = Color.Gray) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { viewModel.processIntents(DataEntryIntents.MenuVerified) }// Стандартная высота TextField
                )
                if ( viewModel.state.selectedVerified != null &&

                    viewModel.state.selectedVerified!!.first != "" ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedVerified!!.first,
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

                                    viewModel.processIntents(

                                        DataEntryIntents.DeleteSelectedVerified )

                                })
                    }
                }

                if ( viewModel.state.expendedVerified ) {

                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed ( listOf("Не подтвержен","подтвержден") )

                            { index, item ->

                                Text( text = item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(

                                                DataEntryIntents.SelectVerified( item,index ))

                                        })
                            }
                        }
                    }
                }


                if ( viewModel.state.selectedService != null &&

                    viewModel.state.selectedService!!.comp_project != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    val checkProject =
                        viewModel.state.selectedService!!.comp_project!!.find { it == "Проект" }

                    val checkEntity =
                        viewModel.state.selectedService!!.comp_project!!.find { it == "Компания" }

                    if (checkEntity != null) {


                        OutlinedTextField(

                            value = viewModel.state.legalEntity,

                            onValueChange = { inputText ->

                                viewModel.processIntents(

                                    DataEntryIntents.InputTextLegalEntity(inputText,

                                        listContragents.filter { legalEntity ->
                                            legalEntity.entities?.any { entity ->
                                                // Проверяем, что name не равен null, и затем фильтруем
                                                entity.name?.contains(inputText, ignoreCase = true) == true
                                            } == true // Условие фильтрации, если хотя бы один entity подходит
                                        }
                                    ))

                            },

                            label = { Text("Юр.лица") },

                            trailingIcon = {

                                IconButton(

                                    onClick = {

                                        viewModel.processIntents(DataEntryIntents.MenuLegalEntity)

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

                        if (viewModel.state.selectedLegalEntity != null) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier.padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                            ) {

                                Text(
                                    text = viewModel.state.selectedLegalEntity!!.name?:"",
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

                                                DataEntryIntents.DeleteSelectedLegalEntity
                                            )

                                        })
                            }
                        }

                        if (viewModel.state.expendedLegalEntity) {

                            Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

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

                                        item.entities!!.forEach { entity ->

                                            if ( entity.name != null && entity.name != "" ) {

                                                Text(entity.name,
                                                    fontSize = 20.sp,
                                                    modifier = Modifier.fillMaxWidth(0.9f)
                                                        .padding(16.dp)
                                                        .clickable(
                                                            indication = null, // Отключение эффекта затемнения
                                                            interactionSource = remember { MutableInteractionSource() })

                                                        {

                                                            viewModel.processIntents(

                                                                DataEntryIntents.SelectLegalEntity(

                                                                  entity, item
                                                                )
                                                            )

                                                        })
                                            }
                                        }

                                    }
                                }
                            }
                        }

                        /*Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(

                            value = viewModel.state.employee,

                            onValueChange = { inputText ->

                                viewModel.processIntents(DataEntryIntents.InputTextEmployee(
                                    inputText,

                                    listEmployee.filter {

                                        it.name!!.contains(inputText, ignoreCase = true)
                                    }))

                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                                //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                                //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                            ),

                            label = { Text("Сотрудник") },

                            trailingIcon = {

                                IconButton(

                                    onClick = {

                                        viewModel.processIntents(DataEntryIntents.MenuEmployee)

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

                        if (viewModel.state.selectedEmployee != null) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier.padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                            ) {
                                Text(
                                    text = viewModel.state.selectedEmployee!!.name ?: "нет имени",
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

                                                DataEntryIntents.DeleteSelectedEmployee
                                            )

                                        })
                            }
                        }

                        if (viewModel.state.expendedEmployee) {

                            Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                                Card(
                                    modifier = Modifier.fillMaxSize()
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                    backgroundColor = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {}
                                LazyColumn {
                                    itemsIndexed(viewModel.state.filteredListEmployee)

                                    { index, item ->

                                        if ( item.contragents == viewModel.state.selectedContragent?.id) {

                                            Text(item.name ?: "нет имени",
                                                fontSize = 20.sp,
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                                    .padding(16.dp)
                                                    .clickable(
                                                        indication = null, // Отключение эффекта затемнения
                                                        interactionSource = remember { MutableInteractionSource() })

                                                    {

                                                        viewModel.processIntents(
                                                            DataEntryIntents.SelectEmployee(
                                                                item
                                                            )
                                                        )

                                                    })
                                        }
                                    }
                                }
                            }
                        }*/

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(

                            value = viewModel.state.location,

                            onValueChange = { inputText ->

                                viewModel.processIntents(DataEntryIntents.InputTextLocation(
                                    inputText,

                                    listLocations.filter {

                                        it.name!!.contains(inputText, ignoreCase = true)
                                    }))

                            },

                            label = { Text("Юр.лица \nЛокация") },

                            trailingIcon = {

                                IconButton(

                                    onClick = {

                                        viewModel.processIntents(DataEntryIntents.MenuLocations)

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

                        if ( viewModel.state.selectedLocation != null ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier.padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                            ) {
                                Text(
                                    text = viewModel.state.selectedLocation!!.name ?: "нет имени",
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

                                                DataEntryIntents.DeleteSelectedLocation)

                                        })
                            }
                        }

                        if (viewModel.state.expendedLocations) {

                            Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                                viewModel.state.filteredListLocations.size).dp)) {
                                Card(
                                    modifier = Modifier.fillMaxSize()
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                    backgroundColor = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {}
                                LazyColumn {

                                    itemsIndexed( viewModel.state.filteredListLocations )

                                    { index, item ->

                                        if ( viewModel.state.selectedContragent != null &&

                                            viewModel.state.selectedContragent?.name == item.contragent?.name ) {

                                            Text(item.name ?: "нет имени",
                                                fontSize = 20.sp,
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                                    .padding(16.dp)
                                                    .clickable(
                                                        indication = null, // Отключение эффекта затемнения
                                                        interactionSource = remember { MutableInteractionSource() })

                                                    {

                                                        viewModel.processIntents(
                                                            DataEntryIntents.SelectLocation(
                                                                item
                                                            )
                                                        )

                                                    })
                                        }

                                        else if ( viewModel.state.selectedContragent == null ) {

                                            Text(item.name ?: "нет имени",
                                                fontSize = 20.sp,
                                                modifier = Modifier.fillMaxWidth(0.9f)
                                                    .padding(16.dp)
                                                    .clickable(
                                                        indication = null, // Отключение эффекта затемнения
                                                        interactionSource = remember { MutableInteractionSource() })

                                                    {

                                                        viewModel.processIntents(
                                                            DataEntryIntents.SelectLocation(
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

                            value = viewModel.state.groupEntity,

                            onValueChange = { inputText ->

                                viewModel.processIntents(DataEntryIntents.InputTextGroupEntity(

                                    inputText, listGroupEntity.filter {

                                        it.name.contains(inputText, ignoreCase = true) }))

                            },

                            label = { Text("Группы юр.лиц") },

                            trailingIcon = {

                                IconButton(

                                    onClick = {

                                        viewModel.processIntents(DataEntryIntents.MenuGroupEntity)

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

                        if ( viewModel.state.selectedGroupEntity != null ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                                    .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                            ) {
                                Text(
                                    text = viewModel.state.selectedGroupEntity!!.name?:"нет имени",
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

                                            viewModel.processIntents(

                                                DataEntryIntents.DeleteSelectedGroupEntity)

                                        })
                            }
                        }

                        if ( viewModel.state.expendedGroupEntity ) {

                            Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                                viewModel.state.filteredListGroupEntity.size).dp)) {
                                Card(
                                    modifier = Modifier.fillMaxSize()
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                    backgroundColor = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {}
                                LazyColumn {
                                    itemsIndexed( viewModel.state.filteredListGroupEntity )

                                    { index, item ->

                                        Text(item.name,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember {

                                                        MutableInteractionSource() })

                                                {

                                                    viewModel.processIntents(

                                                        DataEntryIntents.SelectGroupEntity( item ))

                                                })
                                    }
                                }
                            }
                        }

                    }

                    if ( checkProject != null ) {

                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(

                            value = viewModel.state.project,

                            onValueChange = { inputText ->

                                viewModel.processIntents(

                                    DataEntryIntents.InputTextProject(inputText,

                                        listProjects.filter {

                                            it.name.contains(inputText, ignoreCase = true) } ))

                            },

                            label = { Text("Проект") },

                            trailingIcon = {

                                IconButton(

                                    onClick = {

                                        viewModel.processIntents(DataEntryIntents.MenuProject)

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

                        )

                        if (viewModel.state.selectedProject != null) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier.padding(vertical = 5.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                            ) {

                                Text(
                                    text = viewModel.state.selectedProject!!.name,
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

                                                DataEntryIntents.DeleteSelectedProject
                                            )

                                        })
                            }
                        }

                        if (viewModel.state.expendedProject) {

                            Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                                viewModel.state.filteredListProjects.size).dp)) {
                                Card(
                                    modifier = Modifier.fillMaxSize()
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                    backgroundColor = Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                ) {}
                                LazyColumn {
                                    itemsIndexed(viewModel.state.filteredListProjects)

                                    { index, item ->

                                            if (  item.name != "" ) {

                                                Text(item.name,
                                                    fontSize = 20.sp,
                                                    modifier = Modifier.fillMaxWidth(0.9f)
                                                        .padding(16.dp)
                                                        .clickable(
                                                            indication = null, // Отключение эффекта затемнения
                                                            interactionSource = remember { MutableInteractionSource() })

                                                        {

                                                            viewModel.processIntents(

                                                                DataEntryIntents.SelectProject(

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

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.legalEntityPerformer,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextLegalEntityPerformer( inputText,

                                 listContragents.filter { legalEntity ->
                                    legalEntity.entities?.any { entity ->
                                        // Проверяем, что name не равен null, и затем фильтруем
                                        entity.name?.contains(inputText, ignoreCase = true) == true
                                    } == true // Условие фильтрации, если хотя бы один entity подходит
                                } ))

                    },

                    label = { Text("Юр.лицо исполнитель") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuLegalEntityPerformer)

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

                if ( viewModel.state.selectedLegalEntityPerformer != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {

                            Text(
                                text = viewModel.state.selectedLegalEntityPerformer!!.name?:"",
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

                                    viewModel.processIntents (

                                        DataEntryIntents.DeleteSelectedLegalEntityPerformer )

                                })
                    }
                }

                if ( viewModel.state.expendedLegalEntityPerformer ) {

                    Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                        viewModel.state.filteredListContragents.size).dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListContragents )

                            { index, item ->

                                item.entities!!.forEach { entity ->

                                    if (entity.name != null && entity.name != "" ) {

                                        Text(entity.name,
                                            fontSize = 20.sp,
                                            modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                .clickable(
                                                    indication = null, // Отключение эффекта затемнения
                                                    interactionSource = remember { MutableInteractionSource() })

                                                {

                                                    viewModel.processIntents(

                                                        DataEntryIntents.SelectLegalEntityPerformer(

                                                           entity, item
                                                        )
                                                    )

                                                })

                                    }
                                }

                            }
                        }
                    }
                }

                /*Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.employeePerformer,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextEmployeePerformer( inputText,

                            listEmployee.filter {

                                it.name!!.contains(inputText, ignoreCase = true) } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Сотрудник") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuEmployeePerformer)

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

                if ( viewModel.state.selectedEmployeePerformer != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedEmployeePerformer!!.name?:"нет имени",
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

                                    viewModel.processIntents(

                                        DataEntryIntents.DeleteSelectedEmployeePerformer)

                                })
                    }
                }

                if ( viewModel.state.expendedEmployeePerformer ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListEmployee )

                            { index, item ->

                                if ( item.contragents == viewModel.state.selectedContragentPerformer?.id ) {

                                    Text(item.name ?: "нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(
                                                    DataEntryIntents.SelectEmployeePerformer(
                                                        item
                                                    )
                                                )

                                            })
                                }
                            }
                        }
                    }
                }*/

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.locationPerformer,

                    onValueChange = { inputText ->

                        viewModel.processIntents( DataEntryIntents.InputTextLocationPerformer( inputText,

                            listLocations.filter {

                                it.name!!.contains(inputText, ignoreCase = true) } ))

                    },

                    label = { Text("Юр.лицо исполнитель \nЛокация") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuLocationsPerformer)

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

                if ( viewModel.state.selectedLocationPerformer != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedLocationPerformer!!.name?:"нет имени",
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

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedLocationPerformer)

                                })
                    }
                }

                if ( viewModel.state.expendedLocationsPerformer ) {

                    Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                        viewModel.state.filteredListLocations.size).dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListLocations )

                            { index, item ->

                                if ( viewModel.state.selectedContragentPerformer != null &&

                                    viewModel.state.selectedContragentPerformer!!.name == item.contragent?.name) {

                                    Text(item.name ?: "нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(
                                                    DataEntryIntents.SelectLocationPerformer(
                                                        item
                                                    )
                                                )

                                            })
                                }

                                else if ( viewModel.state.selectedContragentPerformer == null ) {

                                    Text(item.name ?: "нет имени",
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(
                                                    DataEntryIntents.SelectLocationPerformer(
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

                OutlinedTextField(

                    value = viewModel.state.specification,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextSpecification( inputText,

                                listSpecifications.filter {

                                    it.text!!.contains(inputText, ignoreCase = true) } ))

                    },

                    label = { Text("Спецификация") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuSpecifications)

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

                if ( viewModel.state.selectedSpecific != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedSpecific!!.text?:"нет имени",
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

                                    viewModel.processIntents(

                                        DataEntryIntents.DeleteSelectedSpecification)

                                })
                    }
                }

                if ( viewModel.state.expendedSpecifications ) {

                    Box(modifier = Modifier.fillMaxWidth().height(boxHeight(

                        viewModel.state.filteredListSpecifications.size).dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListSpecifications )

                            { index, item ->

                                Text(item.text?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectSpecification( item ))


                                        })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = "",

                    onValueChange = { inputText ->

                    },

                    label = { Text("Статус", color = Color.Gray) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { viewModel.processIntents(DataEntryIntents.MenuStatus) },

                    enabled = false// Стандартная высота TextField
                )

                if ( viewModel.state.selectedStatus.first != "" ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedStatus.first,
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

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedStatus)

                                })
                    }
                }

                if ( viewModel.state.expendedStatus ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed ( listOf(
                                "Завершена", "Активна", "В работе", "Срочная", "Не выполнена",

                                "Выполнена не до конца", "Отложена"
                            ) )

                            { index, item ->

                                Text( text = item,
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectStatus( item,index ))

                                        })
                            }
                        }
                    }
                }

                /*Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.cargo,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextCargo( inputText,

                                listCargo.filter {

                                    it.name!!.contains(inputText, ignoreCase = true) } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Груз") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuCargo)

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

                if ( viewModel.state.selectedCargo != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedCargo!!.name?:"нет имени",
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

                                    viewModel.processIntents(

                                        DataEntryIntents.DeleteSelectedCargo )

                                })
                    }
                }

                if ( viewModel.state.expendedCargo ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListCargo )

                            { index, item ->

                                Text(item.name?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectCargo( item ))


                                        })
                            }
                        }
                    }
                }*/

                if ( viewModel.state.selectedService != null &&

                    viewModel.state.selectedService!!.items != null ) {

                    viewModel.state.selectedService!!.items!!.forEachIndexed { mainIndex,it ->

                        Spacer(modifier = Modifier.height(10.dp))

                        if (it.type == "text" || it.type == "alltext" || it.type == "number" ||

                            it.type == "date" || it.type == "coordinate"
                        ) {

                            OutlinedTextField(

                                value = (viewModel.state.textFieldsValues[mainIndex] as? String)?: "",

                                onValueChange = {

                                    viewModel.processIntents(

                                        DataEntryIntents.InputTextAdditionalFields( it, mainIndex ))

                                },

                                colors = TextFieldDefaults.outlinedTextFieldColors(

                                    backgroundColor = Color.LightGray.copy(alpha = 0.5f) // Цвет фона с легкой прозрачностью
                                ),

                                label = { Text(it.name ?: "") },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 40.dp)
                            )
                        }

                        else if ( it.type == "man" || it.type == "specification" ||

                            it.type == "local" || it.type == "product" || it.type == "project" ||

                            it.type == "company" || it.type == "cargo" ) {

                            var expendedMenu by remember { mutableStateOf(false) }

                            var selectedItem by remember { mutableStateOf<Any?>(viewModel.state.updatedSelectItem) }

                            OutlinedTextField(

                                value = "",

                                onValueChange = {


                                },

                                label = { Text(it.name?:"") },

                                trailingIcon = {

                                    IconButton(

                                        onClick = {

                                            expendedMenu = !expendedMenu

                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(Res.drawable.down_arrow),
                                            contentDescription = "Поиск",
                                            modifier = Modifier.size(20.dp)
                                        )
                                    }
                                },

                                colors = TextFieldDefaults.outlinedTextFieldColors(

                                    backgroundColor = Color.LightGray.copy(alpha = 0.5f) // Цвет фона с легкой прозрачностью
                                ),

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 40.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { }// Стандартная высота TextField
                            )

                            if ( selectedItem != null ) {

                                Spacer(modifier = Modifier.height(10.dp))

                                Box(
                                    modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                                        .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                                ) {
                                    Text(
                                        text = when {
                                            selectedItem is ValDetailModel && it.type == "specification" -> (selectedItem as? ValDetailModel)?.text ?: "нет имени"
                                            selectedItem is ValDetailModel && it.type != "specification" -> (selectedItem as? ValDetailModel)?.name ?: "нет имени"
                                            selectedItem is UserCRMModel && it.type == "man" -> (selectedItem as? UserCRMModel)?.name ?: "нет имени"
                                            selectedItem is SpecificResponseModel && it.type == "specification" -> (selectedItem as? SpecificResponseModel)?.text ?: "нет имени"
                                            selectedItem is LocationResponseModel && it.type == "local" -> (selectedItem as? LocationResponseModel)?.name ?: "нет имени"
                                            selectedItem is ProductModel && it.type == "product" -> (selectedItem as? ProductModel)?.name ?: "нет имени"
                                            selectedItem is ProjectResponseModel && it.type == "project" -> (selectedItem as? ProjectResponseModel)?.name ?: "нет имени"
                                            selectedItem is ContragentResponseModel && it.type == "company" -> (selectedItem as? ContragentResponseModel)?.name ?: "нет имени"
                                            selectedItem is CargoResponseModel && it.type == "cargo" -> (selectedItem as? CargoResponseModel)?.name ?: "нет имени"
                                            else -> "нет имени"
                                        },
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
                                                selectedItem = null

                                            })
                                }
                            }

                            if ( expendedMenu ) {

                                Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                                    Card(
                                        modifier = Modifier.fillMaxSize()
                                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                                        backgroundColor = Color.White,
                                        shape = RoundedCornerShape(8.dp)
                                    ) {}

                                    it.type == "man" || it.type == "specification" ||

                                            it.type == "local" || it.type == "product" || it.type == "project" ||

                                            it.type == "company" || it.type == "cargo"

                                    LazyColumn {

                                        itemsIndexed(
                                            when (it.type) {

                                                "man" -> listEmployee
                                                "specification" -> listSpecifications
                                                "local" -> listLocations
                                                "product" -> listProducts
                                                "project" -> listProjects
                                                "company" -> listContragents
                                                "cargo" -> listCargo
                                                else -> emptyList()

                                            }
                                        )

                                        { index, item ->

                                            Text(when (it.type) {

                                                "man" -> {// Здесь предполагается, что item - это объект из listEmployee
                                                    (item as? UserCRMModel)?.name ?: "нет имени"}
                                                "specification" -> { (item as? SpecificResponseModel)?.text ?: "нет имени" }
                                                "local" -> { (item as? LocationResponseModel)?.name ?: "нет имени" }
                                                "product" -> { (item as? ProductModel)?.name ?: "нет имени" }
                                                "project" -> { (item as? ProjectResponseModel)?.name ?: "нет имени" }
                                                "company" -> { (item as? ContragentResponseModel)?.name ?: "нет имени" }
                                                "cargo" -> { (item as? CargoResponseModel)?.name ?: "нет имени"}
                                                else -> ""

                                            },
                                                fontSize = 20.sp,
                                                modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                                    .clickable(
                                                        indication = null, // Отключение эффекта затемнения
                                                        interactionSource = remember { MutableInteractionSource() })

                                                    {

                                                        selectedItem = item
                                                        expendedMenu = false

                                                        val newList = viewModel.state.textFieldsValues.toMutableList()

                                                        newList[mainIndex] = "${when (it.type) {

                                                            "man" -> {// Здесь предполагается, что item - это объект из listEmployee
                                                                (item as? UserCRMModel)?.id ?:0}
                                                            "specification" -> { (item as? SpecificResponseModel)?.id ?:0 }
                                                            "local" -> { (item as? LocationResponseModel)?.id ?:0 }
                                                            "product" -> { (item as? ProductModel)?.id ?:0 }
                                                            "project" -> { (item as? ProjectResponseModel)?.id ?:0 }
                                                            "company" -> { (item as? ContragentResponseModel)?.id ?:0 }
                                                            "cargo" -> { (item as? CargoResponseModel)?.id ?:0}
                                                            else -> ""

                                                        }}"

                                                        viewModel.state = viewModel.state.copy(

                                                            textFieldsValues = newList

                                                        )

                                                    })
                                        }
                                    }
                                }
                            }

                        }

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.statusText,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextStatusText( inputText ))

                    },

                    label = { Text("Статус") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.task,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextTask( inputText ))

                    },

                    label = { Text("Задача") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)

                )


                Spacer(modifier = Modifier.height(20.dp))

                if ( item == null ) {

                    Button(

                        onClick = {

                            if (viewModel.state.selectedService != null) {

                                viewModel.processIntents(DataEntryIntents.TotalPrice)

                                onClickCreate(

                                    scope,

                                    viewModel.state.selectedService!!.id,

                                    if (viewModel.state.selectedPaidFor != null) viewModel.state.selectedPaidFor!!.second else null,

                                    if (viewModel.state.selectedVerified != null) viewModel.state.selectedVerified!!.second else null,

                                    viewModel.state.task,

                                    if (viewModel.state.selectedLocation != null) viewModel.state.selectedLocation!!.id
                                        ?: 0 else null,

                                    if (viewModel.state.selectedGroupEntity != null) viewModel.state.selectedGroupEntity!!.id else null,

                                    if (viewModel.state.selectedLocationPerformer != null) viewModel.state.selectedLocationPerformer?.id
                                        ?: 0 else null,

                                    viewModel.state.statusText,

                                    "${viewModel.state.totalPrice ?: 0.0}",
                                    null,

                                    if (viewModel.state.selectedSpecific != null) viewModel.state.selectedSpecific!!.id else null,

                                    if (viewModel.state.selectedProject != null) viewModel.state.selectedProject!!.id else null,

                                    if (viewModel.state.selectedLegalEntity != null) viewModel.state.selectedLegalEntity!!.id
                                        ?: 0 else null,

                                    if (viewModel.state.selectedLegalEntityPerformer != null) viewModel.state.selectedLegalEntityPerformer!!.id else viewModel.state.selectedService!!.default_entity_id,

                                    if (viewModel.state.selectedService != null) viewModel.state.selectedService!!.text else "",

                                    viewModel.state.selectedStatus.second ,

                                    viewModel.state.selectedService!!.items?.mapIndexed { index,it ->

                                        ServiceItemCreateCRMModel(

                                            type_id = it.id,

                                            name = viewModel.state.textFieldsValues[index]

                                        )

                                    }


                                )

                            } else {

                                viewModel.processIntents(DataEntryIntents.ColorTF)

                            }

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

                            if (viewModel.state.selectedService != null) {

                                viewModel.processIntents(DataEntryIntents.TotalPrice)

                                onClickUpdate(

                                    scope,

                                    viewModel.state.selectedService!!.ui?:"",

                                     viewModel.state.selectedService!!.id,

                                    if (viewModel.state.selectedPaidFor != null) viewModel.state.selectedPaidFor!!.second else null,

                                    if (viewModel.state.selectedVerified != null) viewModel.state.selectedVerified!!.second else null,

                                    viewModel.state.task,

                                    if (viewModel.state.selectedLocation != null) viewModel.state.selectedLocation!!.id
                                        ?: 0 else null,

                                    if (viewModel.state.selectedGroupEntity != null) viewModel.state.selectedGroupEntity!!.id else null,

                                    if (viewModel.state.selectedLocationPerformer != null) viewModel.state.selectedLocationPerformer?.id
                                        ?: 0 else null,

                                    viewModel.state.statusText,

                                    "${viewModel.state.totalPrice ?: 0.0}",
                                    null,

                                    if (viewModel.state.selectedSpecific != null) viewModel.state.selectedSpecific!!.id else null,

                                    if (viewModel.state.selectedProject != null) viewModel.state.selectedProject!!.id else null,

                                    if (viewModel.state.selectedLegalEntity != null) viewModel.state.selectedLegalEntity!!.id
                                        ?: 0 else null,

                                    if (viewModel.state.selectedLegalEntityPerformer != null) viewModel.state.selectedLegalEntityPerformer!!.id else viewModel.state.selectedService!!.default_entity_id,

                                    if (viewModel.state.selectedService != null) viewModel.state.selectedService!!.text else "",

                                     viewModel.state.selectedStatus.second ,

                                    viewModel.state.selectedService!!.items?.mapIndexed { index,it ->

                                     ServiceItemCreateCRMModel (

                                         type_id = item.value!![index].id,

                                         name = viewModel.state.textFieldsValues[index]

                                     )

                                    }


                                )


                            } else {

                                viewModel.processIntents(DataEntryIntents.ColorTF)

                            }

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