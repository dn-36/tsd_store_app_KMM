package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import kotlinx.coroutines.CoroutineScope
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.cancel
import project.core.resources.dots


class AddProductsComponent(

    val isUpdate: Boolean,

    val listSelectedProducts: List<ProductArrivalAndConsumption>,

    val onClickSelectFromList: (scope: CoroutineScope) -> Unit,

    val onClickCreate: (scope: CoroutineScope) -> Unit,

    val onClickBack: () -> Unit,

    val onClickUpdate: (scope: CoroutineScope) -> Unit,

    val onClickCansel: ( index:Int ) -> Unit

) : Screen {

    var expendedMenu by mutableStateOf(false)

    @Composable

    override fun Content() {

        val scope = rememberCoroutineScope()

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                Row(modifier = Modifier.fillMaxWidth()) {
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

                if(listSelectedProducts.size != 0) {

                    LazyColumn (modifier = Modifier.fillMaxHeight(0.9f)) {

                        itemsIndexed(listSelectedProducts) {index, item ->

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                Text(
                                    item.product.name!!,
                                    fontSize = 19.sp,
                                    modifier = Modifier.fillMaxWidth(0.75f)
                                )

                                Text(
                                    "${item.count} шт.",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Image(
                                    painterResource(Res.drawable.cancel), contentDescription = null,
                                    modifier = Modifier.size(15.dp).clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { onClickCansel( index ) }
                                )

                            }

                            Spacer(modifier = Modifier.height(20.dp))

                        }
                    }
                }
                else {

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        "Добавьте товар",
                        fontSize = 20.sp
                    )

                }
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter), horizontalAlignment = Alignment.End) {

                if ( expendedMenu ) {

                    Box(modifier = Modifier.fillMaxWidth(0.5f).padding(end = 10.dp).height(100.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(modifier = Modifier.weight(0.5f)) {

                            Text("Сканировать", fontSize = 15.sp)
                        }

                            Spacer(modifier = Modifier.height(25.dp))

                            Box(modifier = Modifier.weight(1f)) {

                                Text("Выбрать из списка",
                                    fontSize = 15.sp,
                                    modifier = Modifier.clickable(
                                        indication = null, // Отключение эффекта затемнения
                                        interactionSource = remember { MutableInteractionSource() })
                                    { onClickSelectFromList(scope) })
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    if ( !isUpdate ) {

                        Button(
                            onClick = { onClickCreate(scope) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(text = "Создать")
                        }

                    }

                    else {

                        Button(
                            onClick = { onClickUpdate(scope) },
                            modifier = Modifier
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth(0.8f)
                        ) {
                            Text(text = "Редактировать")
                        }

                    }


                    Box( modifier = Modifier.weight(1f).clickable(

                        indication = null, // Отключение эффекта затемнения

                        interactionSource = remember { MutableInteractionSource() })

                    { expendedMenu = !expendedMenu }, contentAlignment = Alignment.Center

                    ) {
                        Image(
                            painterResource(Res.drawable.dots), contentDescription = null,

                            modifier = Modifier.size(30.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        }

    }


