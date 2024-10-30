package com.project.core_app.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.plus


    @Composable
fun PlusButton(onClick:()->Unit) {

        Box(modifier = Modifier.fillMaxWidth() ,
            contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier.padding(16.dp)
                    //  .align(alignment)
                    .size(60.dp).clip(CircleShape)
                    .background(Color.White).border(
                        width = 2.dp, color = Color.Black, shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Image(painter = painterResource(Res.drawable.plus), contentDescription = null,
                    modifier = Modifier.size(60.dp)
                        .clickable(
                            indication = null, // Отключение эффекта затемнения
                            interactionSource = remember { MutableInteractionSource() })
                        {
                            onClick()
                        }
                )
            }
        }
    }

