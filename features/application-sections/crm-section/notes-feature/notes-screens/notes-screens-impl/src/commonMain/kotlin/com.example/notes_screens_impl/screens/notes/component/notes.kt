package org.example.project.presentation.crm_feature.notes_screen.model

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp


object Notes{
    @Composable
    fun Content(title:String,description:String,onClick:() -> Unit,date:String){

    Box(modifier = Modifier.padding(bottom = 10.dp).defaultMinSize( minWidth = 170.dp, minHeight = 110.dp)
    .sizeIn(maxWidth = 170.dp, maxHeight = 230.dp)){
        Card(modifier = Modifier
            .clickable(
            indication = null, // Отключение эффекта затемнения
            interactionSource = remember { MutableInteractionSource() })
        { onClick() }
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp)) {

            Column ( modifier = Modifier.fillMaxWidth()

                .align(Alignment.BottomCenter).padding(12.dp), verticalArrangement = Arrangement.SpaceBetween) {

                Text(
                    title, fontSize = 18.sp,maxLines = 1, // Укажите максимальное количество строк
                    overflow = TextOverflow.Ellipsis // Чтобы обрезать текст и добавить многоточие, если он превышает maxLines
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    description, fontSize = 13.sp,maxLines = 4, color = Color.LightGray, // Укажите максимальное количество строк
                    overflow = TextOverflow.Ellipsis // Чтобы обрезать текст и добавить многоточие, если он превышает maxLines
                )

                Spacer(modifier = Modifier.height(15.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(
                        date,
                        fontSize = 12.sp
                    )
                }

            }

        }
    }
    }
}