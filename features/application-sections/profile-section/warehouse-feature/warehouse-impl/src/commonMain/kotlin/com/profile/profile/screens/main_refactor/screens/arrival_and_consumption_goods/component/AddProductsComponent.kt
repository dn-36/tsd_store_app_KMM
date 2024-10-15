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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back
import project.core.resources.dots

class AddProductsComponent ( val onClickSelectFromList:() -> Unit ): Screen {

    var expendedMenu by mutableStateOf(false)

    @Composable
    override fun Content() {

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
            }
            Column(modifier = Modifier.align(Alignment.BottomCenter), horizontalAlignment = Alignment.End) {

                if (expendedMenu) {

                    Box(modifier = Modifier.fillMaxWidth(0.5f).padding(end = 10.dp).height(100.dp)) {
                        Card(
                            modifier = Modifier.fillMaxSize()
                                .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
                            backgroundColor = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ) {}
                        Column(modifier = Modifier.padding(horizontal = 10.dp)) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Text("Сканировать", fontSize = 15.sp)

                            Spacer(modifier = Modifier.height(25.dp))

                            Text("Выбрать из списка", fontSize = 15.sp, modifier = Modifier.clickable(
                                indication = null, // Отключение эффекта затемнения
                                interactionSource = remember { MutableInteractionSource() })
                            { onClickSelectFromList() })

                            Spacer(modifier = Modifier.height(10.dp))

                        }
                    }
                }
                Row(
                    modifier = Modifier.padding(10.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .clip(RoundedCornerShape(70.dp))
                            .height(40.dp)
                            .fillMaxWidth(0.8f)
                    ) {
                        Text(text = "Создать")
                    }

                    Image(
                        painterResource(Res.drawable.dots), contentDescription = null,
                        modifier = Modifier.size(30.dp).clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { expendedMenu = !expendedMenu }
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                }
            }
        }
    }
}

