package com.project.core_app.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ConfirmationDialog(
    title:String,
    question:String,
    agreeButtonText:String,
    dismissedButtonText:String,
    onDelete: () -> Unit,
    onCancel: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, bottom = 10.dp)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    // fontStyle = FontStyle. ,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = question,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { onCancel() },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = dismissedButtonText,
                            modifier = Modifier
                                .padding(25.dp),
                            color = Color.Gray
                        )

                    }




                    Box(
                        modifier =  Modifier
                            .weight(1f)
                            .clickable { onDelete() },
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = agreeButtonText,
                            modifier = Modifier
                                .padding(25.dp),
                            color = Color.Red
                        )


                    }
                }
            }
        }
    }
}