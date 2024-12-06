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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.model.AllProductArrivalAndConsumption
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

class CountProductComponent (

    val listAllProducts: List<AllProductArrivalAndConsumption>,

    val onClickReady:( count: String ) -> Unit ,

    val colorBorderCountTF: Color

  ) {

    var count by mutableStateOf("")

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
                        { }
                    )

                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn { items( listAllProducts ){ item ->

                    Text(text = item.name!!, fontSize = 20.sp, modifier = Modifier.padding(10.dp))

                }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    //.fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(

                            value = count,

                            onValueChange = {

                                count = it

                            },

                            colors = TextFieldDefaults.outlinedTextFieldColors(

                                focusedBorderColor = colorBorderCountTF, // Цвет границы при фокусе

                                unfocusedBorderColor = colorBorderCountTF, // Цвет границы в неактивном состоянии

                                backgroundColor = colorBorderCountTF.copy(alpha = 0.1f) // Цвет фона с легкой прозрачностью

                            ),

                            label = { Text("Количество") },

                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),

                            modifier = Modifier

                                .fillMaxWidth()

                                .heightIn(min = 60.dp) // Стандартная высота TextField
                        )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                            onClick = { onClickReady( count ) },
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .height(40.dp)
                                .fillMaxWidth()
                        ) {
                            Text(text = "Готово")
                        }

                    Spacer(modifier = Modifier.fillMaxHeight(0.07f))

                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.05f)
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
            ){

            }
        }

    }

}