package com.arrival_and_consumption_goods

import android.view.InputDevice
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeviceItem(modifier: Modifier = Modifier, item: InputDevice) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, RoundedCornerShape(16.dp))
            .padding(8.dp)
            .then(modifier)

    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Имя: ${item.name}",
            //style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.width(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Id: ${item.id}",
                // style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.width(32.dp))
            Text(
                text = "Источник: ${getSources(item.sources)}",
                //style = MaterialTheme.typography.titleSmall
            )
        }
    }
}