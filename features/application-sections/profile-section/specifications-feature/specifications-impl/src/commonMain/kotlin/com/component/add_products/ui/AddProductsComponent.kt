package com.component.add_products.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.component.add_products.viewmodel.AddProductsIntents
import com.component.add_products.viewmodel.AddProductsViewModel
import com.model.ElementSpecification
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.ready

class AddProductsComponent (

    val listElementSpecifications: List<ElementSpecification>,

    val onClickChooseProduct: ( list: List<ElementSpecification>, indexMainGroup: Int?,

            byCategory: Float ) -> Unit,

    val onClickBack: () -> Unit,

    val indexMainGroup: Int?,

    val byCategory: Float,

    val onClickSave: (list: List<ElementSpecification> ) -> Unit,

    ) {

    val viewModel = AddProductsViewModel()

    @Composable

    fun Content() {

        viewModel.processIntents( AddProductsIntents.SetScreen(listElementSpecifications,

            indexMainGroup, byCategory ) )

        Box(modifier = Modifier.fillMaxSize().background(Color.White))

        {

            Column(modifier = Modifier.padding(16.dp)) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(Res.drawable.back), contentDescription = null,
                        modifier = Modifier.size(20.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })

                        { onClickBack() }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text("Спецификации", color = Color.Black, fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Выбрать продукт", fontSize = 18.sp,

                    textAlign = TextAlign.Center,

                    modifier = Modifier.fillMaxWidth().clickable(
                        indication = null, // Отключение эффекта затемнения
                        interactionSource = remember { MutableInteractionSource() })

                    {
                        onClickChooseProduct(

                            viewModel.state.listElementSpecification,

                            viewModel.state.indexMainGroup, viewModel.state.byCategory
                        )
                    })


                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable(

                    indication = null, // Отключение эффекта затемнения

                    interactionSource = remember { MutableInteractionSource() })

                { viewModel.processIntents(AddProductsIntents.ByCategory) }) {

                    Text(text = "По категориям", fontSize = 15.sp)

                    Spacer(modifier = Modifier.width(20.dp))

                    Image(
                        painterResource(Res.drawable.ready), contentDescription = null,

                        modifier = Modifier.size(20.dp).alpha(viewModel.state.byCategory)
                    )

                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(

                    value = viewModel.state.nameGroup,

                    onValueChange = {

                        viewModel.processIntents(AddProductsIntents.InputTextNameGroup(it))

                    },

                    label = { Text("Название группы") },

                    textStyle = TextStyle(fontSize = 17.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(

                    onClick = {

                    viewModel.processIntents(AddProductsIntents.AddGroup)

                    },

                    modifier = Modifier
                        .clip(RoundedCornerShape(70.dp))
                        .height(35.dp)
                        .fillMaxWidth()

                ) {

                    Text(text = "Добавить группу", fontSize = 15.sp)

                }

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField (

                    value = viewModel.state.totalAmount,

                    onValueChange = {},

                    label = { Text("Общая цена") },

                    colors = TextFieldDefaults.textFieldColors(

                        backgroundColor = Color.LightGray.copy(alpha = 0.5f)
                    ),

                    textStyle = TextStyle(fontSize = 17.sp),

                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 45.dp)

                )

                if ( viewModel.state.listElementSpecification.size != 0 ) {

                    LazyColumn() {

                        itemsIndexed(viewModel.state.listElementSpecification) {

                                mainIndex, item ->

                            Spacer(modifier = Modifier.height(10.dp))

                            Column {

                                Row(verticalAlignment = Alignment.CenterVertically) {

                                    if (viewModel.state.indexMainGroup != mainIndex) {

                                        Button(

                                            onClick = {

                                                viewModel.processIntents(

                                                    AddProductsIntents.ChooseMainGroup(mainIndex)
                                                )

                                            },

                                            modifier = Modifier
                                                .clip(RoundedCornerShape(70.dp))
                                                .height(35.dp)
                                                .width(100.dp)

                                        ) {

                                            Text(text = "Выбрать", fontSize = 12.sp)

                                        }

                                        Spacer(modifier = Modifier.width(20.dp))

                                        Text(text = "${item.block}", fontSize = 17.sp)

                                    } else {

                                        Text(
                                            text = "${item.block}", fontSize = 17.sp,

                                            color = Color(0xFF32CD32)
                                        )

                                    }

                                }

                                item.product.forEachIndexed { index, it ->

                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = item.product[index].name ?: "Нет имени",
                                        fontSize = 17.sp
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),

                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {

                                        OutlinedTextField(

                                            value = if ( item.count[index].toFloatOrNull() != null )

                                                item.count[index] else "",

                                            onValueChange = {

                                                viewModel.processIntents(

                                                    AddProductsIntents.InputTextCount(
                                                        it,
                                                        index,
                                                        mainIndex
                                                    )
                                                )

                                            },

                                            label = { Text("Кол-во", fontSize = 15.sp) },

                                            keyboardOptions = KeyboardOptions(

                                                keyboardType = KeyboardType.Number
                                            ),

                                            textStyle = TextStyle(fontSize = 15.sp),

                                            modifier = Modifier
                                                .width(90.dp)
                                                .heightIn(min = 30.dp)

                                        )

                                        OutlinedTextField(

                                            value =  if ( item.price_item[index].toFloatOrNull() != null )

                                                item.price_item[index] else "",

                                            onValueChange = {

                                                viewModel.processIntents(

                                                    AddProductsIntents.InputTextPrice(
                                                        it,
                                                        index,
                                                        mainIndex
                                                    )
                                                )

                                            },

                                            label = { Text("Цена шт.", fontSize = 12.sp) },

                                            keyboardOptions = KeyboardOptions(

                                                keyboardType = KeyboardType.Number
                                            ),

                                            textStyle = TextStyle(fontSize = 15.sp),

                                            modifier = Modifier
                                                .width(90.dp)
                                                .heightIn(min = 30.dp)

                                        )

                                        OutlinedTextField(

                                            value = item.totalPrice[index],

                                            onValueChange = {


                                            },

                                            label = { Text("") },

                                            colors = TextFieldDefaults.outlinedTextFieldColors(

                                                backgroundColor = Color.LightGray.copy(alpha = 0.5f)
                                            ),

                                            textStyle = TextStyle(fontSize = 15.sp),

                                            modifier = Modifier
                                                .width(80.dp)
                                                .heightIn(min = 30.dp)

                                        )

                                        OutlinedTextField(

                                            value = if ( item.nds[index].toIntOrNull() != null )

                                                item.nds[index] else "",

                                            onValueChange = {

                                                viewModel.processIntents(

                                                    AddProductsIntents.InputTextNDS(
                                                        it,
                                                        index,
                                                        mainIndex
                                                    )
                                                )

                                            },

                                            label = { Text("НДС", fontSize = 15.sp) },

                                            keyboardOptions = KeyboardOptions(

                                                keyboardType = KeyboardType.Number
                                            ),

                                            textStyle = TextStyle(fontSize = 15.sp),

                                            modifier = Modifier
                                                .width(80.dp)
                                                .heightIn(min = 30.dp)

                                        )

                                    }

                                    OutlinedTextField(

                                        value = item.spectext[index],

                                        onValueChange = {

                                            viewModel.processIntents(

                                                AddProductsIntents.InputTextDescription(
                                                    it,
                                                    index,
                                                    mainIndex
                                                )
                                            )

                                        },

                                        label = { Text("Описание") },

                                        textStyle = TextStyle(fontSize = 17.sp),

                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .heightIn(min = 45.dp)

                                    )

                                }
                            }

                        }

                        // Добавляем кнопку в самом конце LazyColumn
                        item {
                            Spacer(modifier = Modifier.height(10.dp))

                            Button(
                                onClick = {

                                    onClickSave(viewModel.state.listElementSpecification)
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(70.dp))
                                    .height(40.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(text = "Сохранить", fontSize = 15.sp)
                            }
                        }

                    }
                }
                else {

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {

                                  onClickSave(viewModel.state.listElementSpecification)

                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Сохранить", fontSize = 15.sp)
                    }

                }

            }
        }

    }
}