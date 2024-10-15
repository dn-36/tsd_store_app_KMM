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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.back

class ListProductsComponent : Screen {

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

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn { items(listOf("Nika","Nika","Nika")){ item ->

                    Text(text = item, fontSize = 20.sp, modifier = Modifier.padding(10.dp))

                }
                }
            }
        }
    }
}