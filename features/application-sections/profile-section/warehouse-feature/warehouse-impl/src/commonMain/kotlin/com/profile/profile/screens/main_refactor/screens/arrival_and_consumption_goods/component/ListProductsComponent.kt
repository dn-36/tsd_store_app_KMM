package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import org.jetbrains.compose.resources.painterResource
import product_network.model.Product
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.ca
import project.core.resources.cancel

class ListProductsComponent ( val listAllProducts: List<Product>, val onClickBack:() -> Unit,

     val onClickProduct:( selectedProducts: ProductArrivalAndConsumption) -> Unit)  {

    var selectedProduct by mutableStateOf<ProductArrivalAndConsumption?>(null)

    @Composable
     fun Content() {

        Box(modifier = Modifier.fillMaxSize().background(Color.White)) {

            Column(modifier = Modifier.padding(16.dp)) {

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

                LazyColumn { items( listAllProducts ){ item ->

                        Text(text = item.name!!,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {
                                    selectedProduct =
                                        ProductArrivalAndConsumption(product = item, count = 0)

                                    onClickProduct(selectedProduct!!)

                                })

                }
                }
            }
        }
    }
}