package com.project.core_app.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import project.core.resources.Res
import project.core.resources.barcode

@Composable
fun BarCodeVkpComponent(
    barCode:Bitmap?,
    title:Bitmap?,
    heightQRcode:Float
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (barCode != null) {
            Image(
                bitmap = barCode!!.asImageBitmap(),
                modifier = Modifier
                    .width(300.dp)
                    .height(
                        (heightQRcode * 5).dp
                    ),
                contentDescription = "qrCode"
            )
            Spacer(modifier = Modifier.height(22.dp))
            Image(
                bitmap = title!!.asImageBitmap(),
                modifier = Modifier
                    .width(350.dp)
                    .wrapContentHeight(),
                contentDescription = "qrCode"
            )
        } else {
            Image(
                painter = painterResource(Res.drawable.barcode),
                "qrCode"
            )
            Text(
                text = "product",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}