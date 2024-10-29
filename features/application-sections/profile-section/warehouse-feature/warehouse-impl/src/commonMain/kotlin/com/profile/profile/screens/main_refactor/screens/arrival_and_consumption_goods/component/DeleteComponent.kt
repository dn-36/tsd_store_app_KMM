package com.profile.profile.screens.main_refactor.screens.arrival_and_consumption_goods.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope

class DeleteComponent (

    val onClickDelete: ( scope: CoroutineScope) -> Unit,

    val onClickNo: () -> Unit,

    ) {

    @Composable

    fun Content () {

        val scope = rememberCoroutineScope ()

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .alpha(0.6f)
                    //  .clickable { actionCloseSettings() }
                    .background(Color.Black)
            )
            Box(
                modifier = Modifier.clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth(0.85f).background(Color.White)
                    .align(Alignment.Center)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.Center)
                ) {
                    Text("Удаление", fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Вы действительно хотите удалить?", fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(25.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(0.8f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text("Удалить", fontSize = 12.sp, modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { onClickDelete(scope) })

                        Text("Отмена", fontSize = 12.sp, modifier = Modifier.clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        { onClickNo() })
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

}