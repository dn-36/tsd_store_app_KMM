package component.data_entry

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
import component.data_entry.viewmodel.DataEntryIntents
import component.data_entry.viewmodel.DataEntryViewModel
import kotlinx.coroutines.CoroutineScope
import model.EntityContragentModel
import model.LocationResponseModel
import model.ServiceItemCreateCRMModel
import model.ServiceResponseModel
import model.SpecificResponseModel
import model.UserCRMModel
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

    val listLegalEntities: List<EntityContragentModel>,

    val listLocations: List<LocationResponseModel>,

    val onClickCreate: (

        scope: CoroutineScope,

        serviceId: Int?,

        statusPay: Int?,

        verifyPay: Int?,

        task: String?,

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

            listEmployee, listLegalEntities, listLocations ))

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

                    Text("Создание", color = Color.Black, fontSize = 20.sp)

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
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Услуги") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuServices)

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
                        Image(painter = painterResource(Res.drawable.cancel),
                            contentDescription = null,
                            modifier = Modifier.padding(8.dp).size(10.dp).align(Alignment.TopEnd)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedService)

                                })
                    }
                }

                if ( viewModel.state.expendedService ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
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

                                            viewModel.processIntents(DataEntryIntents.SelectService( item ))

                                        })
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

                                listLegalEntities.filter { entity ->
                                    // Проверяем, что name не равен null, и затем фильтруем
                                    entity.name?.contains(inputText, ignoreCase = true) == true
                                } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

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
                                text = viewModel.state.selectedLegalEntityPerformer!!.name!!,
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

                                        DataEntryIntents.DeleteSelectedLegalEntityPerformer)

                                })
                    }
                }

                if ( viewModel.state.expendedLegalEntityPerformer ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListLegalEntities )

                            { index, item ->

                                if ( item.name != null ) {

                                    Text( item.name,
                                        fontSize = 20.sp,
                                        modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                            .clickable(
                                                indication = null, // Отключение эффекта затемнения
                                                interactionSource = remember { MutableInteractionSource() })

                                            {

                                                viewModel.processIntents(
                                                    DataEntryIntents.SelectLegalEntityPerformer(
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

                    value = viewModel.state.employee,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextEmployee( inputText,

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

                if ( viewModel.state.selectedEmployee != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedEmployee!!.name?:"нет имени",
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

                                        DataEntryIntents.DeleteSelectedEmployee)

                                })
                    }
                }

                if ( viewModel.state.expendedEmployee ) {

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

                                Text(item.name?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectEmployee( item ))

                                        })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.location,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextLocation( inputText,

                            listLocations.filter {

                                it.name!!.contains(inputText, ignoreCase = true) } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Юр.лицо исполнитель \nЛокация") },

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
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedLocation!!.name?:"нет имени",
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

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedLocation)

                                })
                    }
                }

                if ( viewModel.state.expendedLocations ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListLocations )

                            { index, item ->

                                Text(item.name?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectLocation( item ))

                                        })
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

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

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

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
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

                    value = viewModel.state.status,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextStatus( inputText,

                            listOf(
                                "Активна", "В работе", "Срочная", "Завершена", "Не выполнена",

                                "Выполнена не до конца", "Отложена"
                            ).filter {

                                it.contains(inputText, ignoreCase = true) } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Статус") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuStatus)

                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.down_arrow),
                                contentDescription = "Статус",
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
                            itemsIndexed ( viewModel.state.filteredListStatus )

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

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.statusText,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextStatusText( inputText ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Статус") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { }// Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.task,

                    onValueChange = { inputText ->

                        viewModel.processIntents(DataEntryIntents.InputTextTask( inputText ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Задача") },

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 40.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { }// Стандартная высота TextField
                )


                Spacer(modifier = Modifier.height(10.dp))

                if ( viewModel.state.selectedService != null &&

                    viewModel.state.selectedService!!.items != null ) {

                    viewModel.state.selectedService!!.items!!.forEach {

                        var text by remember { mutableStateOf("") }

                            OutlinedTextField(

                                value = text,

                                onValueChange = {

                                    text = it

                                },

                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                                    //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                                    backgroundColor = Color.LightGray.copy(alpha = 0.5f) // Цвет фона с легкой прозрачностью
                                ),

                                label = { Text(it.name ?: "") },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 40.dp)
                                    .clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { }// Стандартная высота TextField
                            )

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.goodsAndServices,

                    onValueChange = { inputText ->

                        viewModel.processIntents(

                            DataEntryIntents.InputTextGoodsAndServices( inputText,

                                listSpecifications.filter {

                                    it.text!!.contains(inputText, ignoreCase = true) } ))

                    },

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        //focusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы при фокусе
                        //unfocusedBorderColor = vm.state.listColorBorderTF[1], // Цвет границы в неактивном состоянии
                        //backgroundColor = vm.state.listColorBorderTF[1].copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью
                    ),

                    label = { Text("Товары и Услуги") },

                    trailingIcon = {

                        IconButton(

                            onClick = {

                                viewModel.processIntents(DataEntryIntents.MenuGoodsAndServices)

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

                if ( viewModel.state.selectedGoodsAndServices != null ) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Box(
                        modifier = Modifier.padding(vertical = 5.dp).clip(RoundedCornerShape(10.dp))
                            .height(40.dp).fillMaxWidth().background(Color(0xFFA6D172))
                    ) {
                        Text(
                            text = viewModel.state.selectedGoodsAndServices!!.text?:"нет имени",
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

                                    viewModel.processIntents(DataEntryIntents.DeleteSelectedGoodsAndServices)

                                })
                    }
                }

                if ( viewModel.state.expendedGoodsAndServices ) {

                    Box(modifier = Modifier.fillMaxWidth().height(200.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        LazyColumn {
                            itemsIndexed( viewModel.state.filteredListGoodsAndServices )

                            { index, item ->

                                Text(item.text?:"нет имени",
                                    fontSize = 20.sp,
                                    modifier = Modifier.fillMaxWidth(0.9f).padding(16.dp)
                                        .clickable(
                                            indication = null, // Отключение эффекта затемнения
                                            interactionSource = remember { MutableInteractionSource() })

                                        {

                                            viewModel.processIntents(DataEntryIntents.SelectGoodsAndServices( item ))

                                        })
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(

                    onClick = {

                        if (viewModel.state.selectedService != null) {

                            viewModel.processIntents(DataEntryIntents.TotalPrice)

                            onClickCreate(
                                scope,


                                viewModel.state.selectedService!!.id,

                                0, 0, viewModel.state.task,

                                "${viewModel.state.totalPrice ?: 0.0}", null,

                                if (viewModel.state.selectedSpecific != null) viewModel.state.selectedSpecific!!.id else null,

                                null, null,

                                if (viewModel.state.selectedLegalEntityPerformer != null) viewModel.state.selectedLegalEntityPerformer!!.id else viewModel.state.selectedService!!.default_entity_id,

                                if (viewModel.state.selectedService != null) viewModel.state.selectedService!!.text else "",

                                1,

                                emptyList()

                            )

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
        }
    }
}