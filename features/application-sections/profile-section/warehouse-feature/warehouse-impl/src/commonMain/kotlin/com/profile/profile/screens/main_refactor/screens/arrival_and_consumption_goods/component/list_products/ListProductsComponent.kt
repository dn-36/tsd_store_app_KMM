package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products

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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel.ListProductsIntents
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component.list_products.viewmodel.ListProductsViewModel
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.ProductArrivalAndConsumption
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back


class ListProductsComponent (

    val listAllProducts: List<AllProductArrivalAndConsumption>,

    val onClickBack:() -> Unit,

    val onClickProduct:( selectedProducts: ProductArrivalAndConsumption) -> Unit


)  {

    val vm = ListProductsViewModel()

    @Composable
     fun Content() {

         vm.processIntents ( ListProductsIntents.SetScreen( listAllProducts ) )

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

                OutlinedTextField(

                    value = vm.state.nameProductTF,

                    onValueChange = {inputText ->

                        vm.processIntents( ListProductsIntents.InputText ( text = inputText,

                            listAllProducts = listAllProducts.filter {

                                it.name!!.contains(inputText, ignoreCase = true) }

                            ))

                    },
                    label = { Text("Название") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp) // Стандартная высота TextField
                )

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn { items( vm.state.filteredWarehouse ){ item ->

                        Text(text = item.name!!,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp)
                                .clickable(
                                    indication = null, // Отключение эффекта затемнения
                                    interactionSource = remember { MutableInteractionSource() })

                                {
                                    vm.processIntents(ListProductsIntents.SelectedProduct( item = item ))

                                    onClickProduct( vm.state.selectedProduct!! )

                                })

                }
                }
            }
        }
    }
}