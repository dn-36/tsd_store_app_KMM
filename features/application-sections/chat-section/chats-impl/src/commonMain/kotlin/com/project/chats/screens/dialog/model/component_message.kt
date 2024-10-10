package org.example.project.nika_screens_chats.dialog_feature.model

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun componentMessageNeighbor() {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(0.6f).align(Alignment.CenterStart)
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterStart)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFD2F0B1)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Привет,как дела,что делаешь,как жизнь",
                    modifier = Modifier.padding( 10.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}
@Composable
fun componentMyMessage() {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Box(
            modifier = Modifier.fillMaxWidth(0.6f).align(Alignment.CenterEnd)
        ) {
            Box(
                modifier = Modifier.align(Alignment.CenterEnd)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFD2F0B1)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Привет",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}